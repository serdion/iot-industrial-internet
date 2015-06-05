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

    it('should be able create a new information source', function () {
        browser.get('http://localhost:8080/#/sources/');
        element(by.partialLinkText('Add Information source')).click();
        var name = element(by.model("is_name"));
        var type = element(by.model("is_type"));
        var url = element(by.model("is_url"));
        var frequency = element(by.model("is_freq"));
        name.sendKeys('test');
        type.sendKeys('XML');
        url.sendKeys('http://axwikstr.users.cs.helsinki.fi/data.xml');
        frequency.sendKeys('10');
        element(by.id('roundbutton')).click();
        expect(browser.getTitle()).toEqual('IIFramework');
    });

});