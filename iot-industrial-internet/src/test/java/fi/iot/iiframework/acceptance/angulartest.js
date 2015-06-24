describe('Acceptance testing for the AngularJS web interface', function () {

    // guide to using these acceptance tests:
    // 1) install Protractor by typing "npm install -g protractor" to the command line
    // 2) then type "webdriver-manager update" to the command line
    // 3) now every time you want to run these tests, first type "webdriver-manager start" to start the test server,
    // choose "Run Project" in NetBeans and then type "protractor conf.js" in the 
    // cd iot-industrial-internet/src/test/java/fi/iot/iiframework/acceptance/ folder
    // 4) make sure to choose "Run Project" in Netbeans again before running the tests a second time
    // or there will be errors in the tests

    it('login page should have a title', function () {
        browser.driver.get('http://localhost:8080/login');
        expect(browser.driver.getTitle()).toEqual('Please Sign In');
    });
    
    it('should be able to log in with the default user', function () {
        browser.driver.get('http://localhost:8080/login');
        browser.driver.findElement(by.name('username')).sendKeys('moderator');
        browser.driver.findElement(by.name('password')).sendKeys('moderator');
        browser.driver.findElement(By.xpath("//button[contains(.,'Login')]")).click();
        expect(browser.driver.getTitle()).toEqual('Industrial Internet Framework');
    });

    it('should be able to move to the sources list by clicking the sidebar button', function () {
        browser.driver.get('http://localhost:8080/login');
        browser.driver.findElement(by.name('username')).sendKeys('moderator');
        browser.driver.findElement(by.name('password')).sendKeys('moderator');
        browser.driver.findElement(By.xpath("//button[contains(.,'Login')]")).click();
        element(by.partialLinkText('Information Sources')).click();
        expect(element(by.partialLinkText('Add New')).isPresent()).toBe(true);
    });
    
    if('should not be able to log in with incorrect details', function () {
        // TODO
    });

    it('should be able to move to the errors list, view an error and then return to the errors list', function () {
        browser.driver.get('http://localhost:8080/login');
        browser.driver.findElement(by.name('username')).sendKeys('moderator');
        browser.driver.findElement(by.name('password')).sendKeys('moderator');
        browser.driver.findElement(By.xpath("//button[contains(.,'Login')]")).click();
        element(by.partialLinkText('Errors')).click();
        element(by.id('Details')).click();
        element(by.id('options')).click();
        expect(element(by.partialLinkText('Details')).isPresent()).toBe(true);
    });

//    it('should be able view a sensor and filter its readouts', function () {
//        browser.driver.get('http://localhost:8080/login');
//        browser.driver.findElement(by.name('username')).sendKeys('moderator');
//        browser.driver.findElement(by.name('password')).sendKeys('moderator');
//        browser.driver.findElement(By.xpath("//button[contains(.,'Login')]")).click();
//        element(by.partialLinkText('View')).click();
//        element(by.partialLinkText('List')).click();
//        element(by.model("more")).sendKeys("22.50");
//        element(by.model("less")).sendKeys("22.80");
//        element(by.id('filter')).click();
//        expect(element.all(by.repeater('readout in readouts')).count()).toEqual(4);
//    });
//
//    it('should be able create a new information source', function () {
//        browser.driver.get('http://localhost:8080/login');
//        browser.driver.findElement(by.name('username')).sendKeys('moderator');
//        browser.driver.findElement(by.name('password')).sendKeys('moderator');
//        browser.driver.findElement(By.xpath("//button[contains(.,'Login')]")).click();
//        element(by.partialLinkText('Add New')).click();
//        var name = element(by.model("is.name"));
//        var type = element(by.model("is.type"));
//        var url = element(by.model("is.url"));
//        var frequency = element(by.model("readFrequency_s"));
//        name.sendKeys('NewAcceptanceTestSource');
//        element(by.cssContainingText('option', 'XML')).click();
//        url.sendKeys('http://axwikstr.users.cs.helsinki.fi/data.xml');
//        frequency.sendKeys('15400');
//        element(by.id('submitbutton')).click();
//        element(by.partialLinkText('Configurations')).click();
//        expect(element.all(by.repeater('c in configurations')).count()).toEqual(2);
//    });

    it('should be albe to view occured errors', function () {
        // TODO tee virhe esim restapipyynnöllä ja katso onko virhe ilmoittanut
    });
    
    if('should be notified of unread errors in the topbar', function () {
        // TODO lukematon virhe vaihtaa iconin classia
    });
    
});
