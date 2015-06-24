describe('Acceptance testing for the AngularJS web interface', function () {

    // guide to using these acceptance tests:
    // 1) install Protractor by typing "npm install -g protractor" to the command line
    // 2) then type "webdriver-manager update" to the command line
    // 3) now every time you want to run these tests, first type "webdriver-manager start" to start the test server,
    // choose "Run Project" in NetBeans and then type "protractor conf.js" in the 
    // cd iot-industrial-internet/src/test/java/fi/iot/iiframework/acceptance/ folder
    // 4) make sure to choose "Run Project" in Netbeans again before running the tests a second time
    // or there can/will be errors in the tests

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

    it('should not be able to log in with incorrect details', function () {
        browser.driver.get('http://localhost:8080/login');
        browser.driver.findElement(by.name('username')).sendKeys('moderator');
        browser.driver.findElement(by.name('password')).sendKeys('mderator');
        browser.driver.findElement(By.xpath("//button[contains(.,'Login')]")).click();
        expect(browser.driver.getTitle()).toEqual('Please Sign In');
    });

    it('should be able to move to the sources list by clicking the sidebar button', function () {
        browser.driver.get('http://localhost:8080/login');
        browser.driver.findElement(by.name('username')).sendKeys('moderator');
        browser.driver.findElement(by.name('password')).sendKeys('moderator');
        browser.driver.findElement(By.xpath("//button[contains(.,'Login')]")).click();
        element(by.partialLinkText('Information Sources')).click();
        expect(element(by.partialLinkText('Add New')).isPresent()).toBe(true);
    });

    it('should be able to view occurred errors', function () {
        browser.driver.get('http://localhost:8080/login');
        browser.driver.findElement(by.name('username')).sendKeys('moderator');
        browser.driver.findElement(by.name('password')).sendKeys('moderator');
        browser.driver.findElement(By.xpath("//button[contains(.,'Login')]")).click();
        element(by.partialLinkText('Information Sources')).click();
        element(by.partialLinkText('Read now')).click();
        element(by.partialLinkText('Read now')).click();
        element(by.partialLinkText('Errors')).click();
        element(by.id('Details')).click();
        element(by.id('backbutton')).click();
        expect(element(by.id('Details')).isPresent()).toBe(true);
        expect(element.all(by.repeater('error in errorlist')).count()).toEqual(1);
    });
    
    it('should be notified of unread errors in the topbar', function () {
        browser.driver.get('http://localhost:8080/login');
        browser.driver.findElement(by.name('username')).sendKeys('moderator');
        browser.driver.findElement(by.name('password')).sendKeys('moderator');
        browser.driver.findElement(By.xpath("//button[contains(.,'Login')]")).click();
        element(by.partialLinkText('Information Sources')).click();
        element(by.partialLinkText('Read now')).click();
        element(by.partialLinkText('Read now')).click();
        browser.get("http://localhost:8080/#/sources");
        expect(element(by.css('.unviewederrorsblink')).isPresent()).toBe(true);
    });

    it('should be able view a sensor and filter its readouts', function () {
        browser.driver.get('http://localhost:8080/login');
        browser.driver.findElement(by.name('username')).sendKeys('moderator');
        browser.driver.findElement(by.name('password')).sendKeys('moderator');
        browser.driver.findElement(By.xpath("//button[contains(.,'Login')]")).click();
        element(by.partialLinkText('Information Sources')).click();
        element(by.partialLinkText('View')).click();
        element(by.partialLinkText('List')).click();
        element(by.model("more")).sendKeys("40.0");
        element(by.model("less")).sendKeys("30.0");
        element(by.id('filter')).click();
        expect(element.all(by.repeater('readout in readouts')).count()).toEqual(0);
    });

    it('should be able create a new information source', function () {
        browser.driver.get('http://localhost:8080/login');
        browser.driver.findElement(by.name('username')).sendKeys('moderator');
        browser.driver.findElement(by.name('password')).sendKeys('moderator');
        browser.driver.findElement(By.xpath("//button[contains(.,'Login')]")).click();
        element(by.partialLinkText('Information Sources')).click();
        element(by.partialLinkText('Add New')).click();
        var name = element(by.model("is.name"));
        var type = element(by.model("is.type"));
        var url = element(by.model("is.url"));
        var startDate = element(by.model("is.startDate")); 2015-06-24
        name.sendKeys('NewAcceptanceTestSource');
        element(by.cssContainingText('option', 'JSON')).click();
        url.sendKeys('https://data.sparkfun.com/output/dZ4EVmE8yGCRGx5XRX1W.json?page=1');
        startDate.sendKeys("2015-06-24");
        element(by.id('submitbutton')).click();
        browser.get("http://localhost:8080/#/sources");
        expect(element.all(by.repeater('ds in sources')).count()).toEqual(2);
    });

});