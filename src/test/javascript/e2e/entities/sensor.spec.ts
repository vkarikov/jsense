import { browser, element, by } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';

describe('Sensor e2e test', () => {

    let navBarPage: NavBarPage;
    let sensorDialogPage: SensorDialogPage;
    let sensorComponentsPage: SensorComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Sensors', () => {
        navBarPage.goToEntity('sensor');
        sensorComponentsPage = new SensorComponentsPage();
        expect(sensorComponentsPage.getTitle())
            .toMatch(/jsenseApp.sensor.home.title/);

    });

    it('should load create Sensor dialog', () => {
        sensorComponentsPage.clickOnCreateButton();
        sensorDialogPage = new SensorDialogPage();
        expect(sensorDialogPage.getModalTitle())
            .toMatch(/jsenseApp.sensor.home.createOrEditLabel/);
        sensorDialogPage.close();
    });

    it('should create and save Sensors', () => {
        sensorComponentsPage.clickOnCreateButton();
        sensorDialogPage.setNameInput('name');
        expect(sensorDialogPage.getNameInput()).toMatch('name');
        sensorDialogPage.save();
        expect(sensorDialogPage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class SensorComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-sensor div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class SensorDialogPage {
    modalTitle = element(by.css('h4#mySensorLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    nameInput = element(by.css('input#field_name'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setNameInput = function(name) {
        this.nameInput.sendKeys(name);
    }

    getNameInput = function() {
        return this.nameInput.getAttribute('value');
    }

    save() {
        this.saveButton.click();
    }

    close() {
        this.closeButton.click();
    }

    getSaveButton() {
        return this.saveButton;
    }
}
