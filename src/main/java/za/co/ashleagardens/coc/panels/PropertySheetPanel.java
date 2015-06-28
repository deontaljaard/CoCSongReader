package za.co.ashleagardens.coc.panels;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import za.co.ashleagardens.coc.components.PropertyTable;
import org.apache.commons.configuration.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.ashleagardens.coc.util.PropertyUtil;

/**
 *
 * @author Deon
 */
public class PropertySheetPanel extends javax.swing.JPanel {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertySheetPanel.class);
    private static final String CLASS_NAME = PropertySheetPanel.class.getName();
    private static final PropertyUtil PROPERTY_UTIL = PropertyUtil.INSTANCE;

    private final JDialog propertySheetPanelParent;

    /**
     * Creates new form PropertySheetPanel
     *
     * @param propertySheetPanelParent the parent dialog this property sheet
     * gets added to.
     */
    public PropertySheetPanel(JDialog propertySheetPanelParent) {
        initComponents();

        this.propertySheetPanelParent = propertySheetPanelParent;
        this.propertyTableScrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        propertyTableScrollPane = new javax.swing.JScrollPane();
        try {
            propertyTable = new PropertyTable(PROPERTY_UTIL.getApplicationProperties());
            okayBtn = new javax.swing.JButton();
            cancelBtn = new javax.swing.JButton();

        } catch (ConfigurationException ex) {
            LOGGER.error(ex.getMessage());
        }
        propertyTable.getTableHeader().setReorderingAllowed(false);
        propertyTableScrollPane.setViewportView(propertyTable);

        okayBtn.setText("Okay");
        okayBtn.setToolTipText("Update properties");
        okayBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okayBtnMouseClicked(evt);
            }
        });

        cancelBtn.setText("Cancel");
        cancelBtn.setToolTipText("Close the dialog");
        cancelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(propertyTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelBtn)
                .addGap(5, 5, 5)
                .addComponent(okayBtn)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(propertyTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn)
                    .addComponent(okayBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void okayBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayBtnMouseClicked
        try {
            PROPERTY_UTIL.updatePropertyForApplication(((PropertyTable) propertyTable).getUpdatedPropertyMap());
        } catch (ConfigurationException ex) {
            LOGGER.error(CLASS_NAME + ":okayBtnMouseClicked: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Could not update properties!");
        }
        propertySheetPanelParent.dispose();
    }//GEN-LAST:event_okayBtnMouseClicked

    private void cancelBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelBtnMouseClicked
        this.propertySheetPanelParent.dispose();
    }//GEN-LAST:event_cancelBtnMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton okayBtn;
    private javax.swing.JTable propertyTable;
    private javax.swing.JScrollPane propertyTableScrollPane;
    // End of variables declaration//GEN-END:variables

}
