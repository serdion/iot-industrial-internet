describe('Acceptance testing for the AngularJS web interface', function () {

    it('the web interface should have a title', function () {
        browser.get('http://localhost:8080/#/');
        expect(browser.getTitle()).toEqual('IIFramework');
    });

    it('should be able to move to the sources list by clicking the sidebar button', function () {
        browser.get('http://localhost:8080/#/');
        element(by.partialLinkText('Information Sources')).click();
        expect(browser.getTitle()).toEqual('IIFramework');
    });
    
    it('should be able view a sensor and filter its readouts', function () {
        browser.get('http://localhost:8080/#/sources/');
        element(by.partialLinkText('View')).click();
        element(by.partialLinkText('List')).click();
        element(by.model("more")).sendKeys("22.50");
        element(by.model("less")).sendKeys("22.80");
        element(by.id('filter')).click();
        expect(browser.getTitle()).toEqual('IIFramework');
    });

    it('should be able create a new information source', function () {
        browser.get('http://localhost:8080/#/sources/');
        element(by.partialLinkText('Add New')).click();
        var name = element(by.model("is.name"));
        var type = element(by.model("is.type"));
        var url = element(by.model("is.url"));
        var frequency = element(by.model("is.readFrequency"));
        name.sendKeys('NewAcceptanceTestSource');
        element(by.cssContainingText('option', 'XML')).click();
        url.sendKeys('http://axwikstr.users.cs.helsinki.fi/data.xml');
        frequency.sendKeys('154');
        element(by.id('roundbutton')).click();
        expect(browser.getTitle()).toEqual('IIFramework');
    });
});