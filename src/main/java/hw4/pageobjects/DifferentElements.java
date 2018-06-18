package hw4.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import hw4.enums.CheckBox;
import hw4.enums.DropDown;
import hw4.enums.Radio;
import org.openqa.selenium.support.FindBy;

import java.util.Set;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;

/**
 * Created by Екатерина on 18.06.2018.
 */
public class DifferentElements {
    @FindBy(css = ".label-checkbox")
    private ElementsCollection checkBoxes;

    @FindBy(css = ".label-radio")
    private ElementsCollection radios;

    @FindBy(css = ".colors > [class = uui-form-element]")
    private SelenideElement dropDown;

    @FindBy(css = ".main-content-hg > [class = 'uui-button']")
    private ElementsCollection buttons;

    @FindBy(css = ".right-fix-panel")
    private SelenideElement rightSection;

    @FindBy(css = ".mCSB_vertical")
    private SelenideElement leftSection;

    @FindBy(css = "option")
    private ElementsCollection dropDownOptions;

    @FindBy(css = ".logs > li")
    private ElementsCollection logCollection;

    public void shouldHasAllNeededElements() {
        checkBoxes.shouldHaveSize(4);
        radios.shouldHaveSize(4);
        dropDown.shouldBe(visible);
        buttons.shouldHaveSize(2);
    }

    public void shouldHasLeftSection() {
        leftSection.shouldBe(visible);
    }

    public void shouldHasRightSection() {
        rightSection.shouldBe(visible);
    }

    public void selectCheckBox(CheckBox checkBoxName) {
        checkBoxes.get(checkBoxName.index).click();
    }

    public void selectRadio(Radio radio) {
        radios.get(radio.index).click();
    }

    public void selectDropDown(DropDown dropDownName) {
        dropDown.click();
        dropDownOptions.get(dropDownName.index).click();
    }

    public void select(Object[] elements) {
        if (elements.length == 1) {
            if (elements[0] instanceof DropDown) {
                selectDropDown((DropDown) elements[0]);
            }
            if (elements[0] instanceof Radio) {
                selectRadio((Radio) elements[0]);
            }
        } else {
            for (Object checkBoxName : elements) {
                selectCheckBox((CheckBox) checkBoxName);
            }
        }
    }

    public void shouldHasCorrectLogRow(Object[] elements) {
        if (elements.length == 1) {
            if (elements[0] instanceof DropDown) {
                DropDown newObject = (DropDown) elements[0];
                logCollection.get(0).should(matchText("\\d\\d:\\d\\d:\\d\\d Colors: value changed to "
                        + newObject.name));
            }
            if (elements[0] instanceof Radio) {
                Radio newObject = (Radio) elements[0];
                logCollection.get(0).should(matchText("\\d\\d:\\d\\d:\\d\\d metal: value changed to "
                        + newObject.name));
            }
        } else {
            for (int i = 0; i < elements.length; i++) {
                CheckBox newObject = (CheckBox) elements[elements.length - 1 - i];
                System.out.println(checkBoxes.get(newObject.index).is(checked));

                logCollection.get(i).should(matchText("\\d\\d:\\d\\d:\\d\\d "
                                + newObject.name + ": condition changed to " + ".*"
                        //DID NOT MANAGE HOW TO CHECK IF IT IS CHECKED + checkBoxes.get(newObject.index).is(checked)
                ));
            }
        }
    }

}