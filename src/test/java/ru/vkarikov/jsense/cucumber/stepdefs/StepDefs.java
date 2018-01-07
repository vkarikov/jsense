package ru.vkarikov.jsense.cucumber.stepdefs;

import ru.vkarikov.jsense.JsenseApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = JsenseApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
