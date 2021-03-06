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
package com.github.alexfalappa.nbspringboot.projects.customizer;

import javax.swing.JComponent;

import org.apache.maven.model.Dependency;
import org.apache.maven.project.MavenProject;
import org.netbeans.api.project.Project;
import org.netbeans.modules.maven.api.NbMavenProject;
import org.netbeans.modules.maven.api.customizer.ModelHandle2;
import org.netbeans.spi.project.ui.support.ProjectCustomizer;
import org.openide.util.Lookup;

/**
 * Creates the Spring Boot customizer panel when maven projects have a dependency on dev tools.
 *
 * @author Alessandro Falappa
 */
@ProjectCustomizer.CompositeCategoryProvider.Registration(projectType = "org-netbeans-modules-maven", position = 1300)
public class BootPanelProvider implements ProjectCustomizer.CompositeCategoryProvider {

    @Override
    public ProjectCustomizer.Category createCategory(Lookup context) {
        if (prjHasDepContaining(context, "spring-boot")) {
            return ProjectCustomizer.Category.create("boot", "Spring Boot", null);
        }
        return null;
    }

    @Override
    public JComponent createComponent(ProjectCustomizer.Category category, Lookup context) {
        ModelHandle2 mh2 = context.lookup(ModelHandle2.class);
        final BootPanel bootPanel = new BootPanel();
        bootPanel.setModelHandle(mh2);
        bootPanel.setDevToolsVisible(prjHasDepContaining(context, "devtools"));
        return bootPanel;
    }

    private boolean prjHasDepContaining(Lookup context, String txt) {
        Project prj = context.lookup(Project.class);
        if (prj != null) {
            NbMavenProject nbMvn = prj.getLookup().lookup(NbMavenProject.class);
            if (nbMvn != null) {
                MavenProject mvnPrj = nbMvn.getMavenProject();
                for (Object o : mvnPrj.getDependencies()) {
                    Dependency d = (Dependency) o;
                    if (d.getArtifactId().contains(txt)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
