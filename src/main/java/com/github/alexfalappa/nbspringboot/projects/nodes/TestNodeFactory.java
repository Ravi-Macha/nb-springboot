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
package com.github.alexfalappa.nbspringboot.projects.nodes;

import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ui.support.NodeFactory;
import org.netbeans.spi.project.ui.support.NodeFactorySupport;
import org.netbeans.spi.project.ui.support.NodeList;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;

import com.github.alexfalappa.nbspringboot.api.TestService;

/**
 *
 * @author Alessandro Falappa
 */
@NodeFactory.Registration(projectType = {"org-netbeans-modules-maven"})
public class TestNodeFactory implements NodeFactory {

    @Override
    public NodeList<?> createNodes(Project prj) {
        FileObject foDir = prj.getProjectDirectory().getFileObject("src/main/resources");
        TestService srv = prj.getLookup().lookup(TestService.class);
        if (foDir != null && srv != null) {
            try {
                TestNode tn = new TestNode(prj);
                return NodeFactorySupport.fixedNodeList(tn);
            } catch (DataObjectNotFoundException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        return NodeFactorySupport.fixedNodeList();
    }

}
