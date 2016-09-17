/*
 * Copyright 2016 Alessandro Falappa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.alexfalappa.nbspringboot.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import org.netbeans.api.project.Project;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

import com.github.alexfalappa.nbspringboot.api.TestService;

@ActionID(
        category = "Build",
        id = "com.github.alexfalappa.nbspringboot.actions.TestAction"
)
@ActionRegistration(
        iconBase = "com/github/alexfalappa/nbspringboot/actions/springboot-logo.png",
        displayName = "#CTL_TestAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/BuildProject", position = 58),
    @ActionReference(path = "Toolbars/Build", position = 501)
})
@Messages("CTL_TestAction=Test Action")
public final class TestAction implements ActionListener {

    private static final Logger logger = Logger.getLogger(TestAction.class.getName());
//    private final TestService srv;
    private final Project prj;

    public TestAction(Project srv) {
        this.prj = srv;
    }
//    public TestAction(TestService srv) {
//        this.srv = srv;
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TestService srv = prj.getLookup().lookup(TestService.class);
        if (srv != null) {
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(srv.something()));
        } else {
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message("Niente"));
        }
    }
}
