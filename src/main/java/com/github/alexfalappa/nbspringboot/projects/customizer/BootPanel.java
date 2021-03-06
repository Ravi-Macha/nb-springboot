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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.commons.lang.StringUtils;
import org.netbeans.modules.maven.api.customizer.ModelHandle2;
import org.netbeans.modules.maven.execute.model.ActionToGoalMapping;
import org.netbeans.modules.maven.execute.model.NetbeansActionMapping;
import org.netbeans.spi.project.ActionProvider;

import com.github.alexfalappa.nbspringboot.actions.RestartAction;

import static com.github.alexfalappa.nbspringboot.actions.RestartAction.PROP_RUN_ARGS;
import static com.github.alexfalappa.nbspringboot.actions.RestartAction.TRIGGER_FILE;

/**
 * Customizer panel for maven projects with spring boot devtools dependency.
 *
 * @author Alessandro Falappa
 */
public class BootPanel extends javax.swing.JPanel implements DocumentListener {

    public static final String CMDLINE_RESTART = "--spring.devtools.restart.trigger-file=" + RestartAction.TRIGGER_FILE;
    private static final Logger logger = Logger.getLogger(BootPanel.class.getName());
    private ModelHandle2 mh2;
    private List<String> args = new LinkedList<>();
    private Map<String, String> runProps;
    private Map<String, String> debugProps;

    /** Creates new form BootPanel */
    public BootPanel() {
        initComponents();
    }

    void setDevToolsVisible(boolean visible) {
        lDevtools.setVisible(visible);
        chDevtools.setVisible(visible);
        lDevtoolsWarning.setVisible(visible);
    }

    void setModelHandle(ModelHandle2 mh2) {
        Objects.requireNonNull(mh2);
        // store reference to project properties model and to properties of maven actions for run/debug
        this.mh2 = mh2;
        ActionToGoalMapping mapps = mh2.getActionMappings();
        for (NetbeansActionMapping map : mapps.getActions()) {
            if (map.getActionName().equals(ActionProvider.COMMAND_RUN)) {
                this.runProps = map.getProperties();
            } else if (map.getActionName().equals(ActionProvider.COMMAND_DEBUG)) {
                this.debugProps = map.getProperties();
            }
        }
        // prepare the set of cmd line args
        if (runProps.containsKey(PROP_RUN_ARGS) && runProps.get(PROP_RUN_ARGS) != null) {
            args.addAll(Arrays.asList(runProps.get(PROP_RUN_ARGS).split(",")));
        }
        // make the widget reflect the existing cmd line args
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            if (arg.contains(TRIGGER_FILE)) {
                chDevtools.setSelected(true);
            } else {
                sb.append(arg).append(' ');
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        txArgs.setText(sb.toString());
        // hook up to command line arguments textfield
        txArgs.getDocument().addDocumentListener(this);
        // enable widgets
        chDevtools.setEnabled(true);
        txArgs.setEnabled(true);
    }

    /** This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lDevtools = new javax.swing.JLabel();
        chDevtools = new javax.swing.JCheckBox();
        lArgs = new javax.swing.JLabel();
        txArgs = new javax.swing.JTextField();
        lDevtoolsWarning = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(lDevtools, org.openide.util.NbBundle.getBundle(BootPanel.class).getString("BootPanel.lDevtools.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(chDevtools, org.openide.util.NbBundle.getBundle(BootPanel.class).getString("BootPanel.chDevtools.text")); // NOI18N
        chDevtools.setEnabled(false);
        chDevtools.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chDevtoolsActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lArgs, org.openide.util.NbBundle.getBundle(BootPanel.class).getString("BootPanel.lArgs.text")); // NOI18N

        txArgs.setColumns(15);
        txArgs.setEnabled(false);

        lDevtoolsWarning.setFont(lDevtoolsWarning.getFont().deriveFont(lDevtoolsWarning.getFont().getSize()-2f));
        org.openide.awt.Mnemonics.setLocalizedText(lDevtoolsWarning, org.openide.util.NbBundle.getMessage(BootPanel.class, "BootPanel.lDevtoolsWarning.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lDevtoolsWarning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lDevtools)
                            .addComponent(lArgs))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chDevtools)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txArgs))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lArgs)
                    .addComponent(txArgs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lDevtools)
                    .addComponent(chDevtools))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(lDevtoolsWarning)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chDevtoolsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chDevtoolsActionPerformed
        updateCmdLineArgs();
    }//GEN-LAST:event_chDevtoolsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chDevtools;
    private javax.swing.JLabel lArgs;
    private javax.swing.JLabel lDevtools;
    private javax.swing.JLabel lDevtoolsWarning;
    private javax.swing.JTextField txArgs;
    // End of variables declaration//GEN-END:variables

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateCmdLineArgs();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateCmdLineArgs();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateCmdLineArgs();
    }

    private void updateCmdLineArgs() {
        args.clear();
        if (chDevtools.isSelected()) {
            args.add(CMDLINE_RESTART);
        }
        final String txt = txArgs.getText().trim();
        if (!txt.isEmpty()) {
            args.addAll(Arrays.asList(txt.split("\\s+")));
        }
        if (args.isEmpty()) {
            runProps.remove(PROP_RUN_ARGS);
            debugProps.remove(PROP_RUN_ARGS);
        } else {
            final String newVal = StringUtils.join(args, ',');
            if (newVal != null) {
                runProps.put(PROP_RUN_ARGS, newVal);
                debugProps.put(PROP_RUN_ARGS, newVal);
            }
        }
        mh2.markAsModified(mh2.getActionMappings());
        logger.finer(String.format("Command line args: %s", runProps.get(PROP_RUN_ARGS)));
    }

}
