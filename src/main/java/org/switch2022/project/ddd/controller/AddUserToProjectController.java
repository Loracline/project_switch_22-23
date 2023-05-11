package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.ResourceAllocationService;

/**
 * Class AddUserToProjectController is built to allocate a User to a Project.
 */

@Controller
public class AddUserToProjectController {
    @Autowired
    private ResourceAllocationService service;
}
