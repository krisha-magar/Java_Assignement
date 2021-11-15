
package org.personal.bhatbhateni.ui;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.personal.bhatbhateni.model.Products;
import org.personal.bhatbhateni.dao.impl.ProductDaoImpl;
import org.personal.bhatbhaneti.dao.ProductDao;


public class AdminView extends javax.swing.JFrame {

    private final ProductDao productsdao = new ProductDaoImpl();

    private final String[] columns = new String[]{"Id", "ProductName", "ProductCategory", "ProductQuantity", 
        "ProductPrice"};

    private final DefaultTableModel model = new DefaultTableModel();

    /**
     * Creates new form Home
     */
    public AdminView() {
        initComponents();
        setUpTableModel();
        setUpPaddingInTextField();
    }

    private void save() throws NumberFormatException, HeadlessException {
        try {
            Products product = getValueFromTextField();
            int rowCount = productsdao.save(product);
            if (rowCount >= 1) {
                showMessageDialog("Product sucessfully added");
                resetForm();
            } else {
                showMessageDialog("Something went wrong");
            }
        } catch (SQLException | ClassNotFoundException | NumberFormatException ex) {
            showMessageDialog(ex.getMessage());
        }
    }

    private void update() throws NumberFormatException {
        try {
            int selectedRow = productsTable.getSelectedRow();
            int id = (int) productsTable.getValueAt(selectedRow, 0);
            Products product = productsdao.findOne(id);
            if (editOrUpdateButton.getText().equals("Edit")) {
                editOrUpdateButton.setText("Update");
                productNameTextField.setText(product.getproductName());
                productCategoryTextField.setText(product.getproductCategory());
                productQuantityTextField.setText(String.valueOf(product.getproductQuantity()));
                productPriceTextField.setText(String.valueOf(product.getproductPrice()));
            } else if (editOrUpdateButton.getText().equals("Update")) {
                product.setproductName(productNameTextField.getText());
                product.setproductCategory(productCategoryTextField.getText());
                product.setproductQuantity(Integer.valueOf(productQuantityTextField.getText()));
                product.setproductPrice(Double.valueOf(productPriceTextField.getText()));
                int rowCount = productsdao.update(product, id);

                if (rowCount >= 1) {
                    showMessageDialog("Book sucessfully updated");
                    resetForm();
                    findAll();
                    editOrUpdateButton.setText("Edit");
                } else {
                    showMessageDialog("Something went wrong");
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            showMessageDialog(ex.getMessage());
        }
    }

  

    private void remove() {
        int selectedRow = productsTable.getSelectedRow();
        int id = (int) productsTable.getValueAt(selectedRow, 0);
        try {
            Products product = productsdao.findOne(id);
            if (product != null) {
                int rowCount = productsdao.remove(id);
                if (rowCount >= 1) {
                    showMessageDialog("product sucessfully deleted");
                    findAll();
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            showMessageDialog(ex.getMessage());
        }
    }

    private void findAll() {
        model.setRowCount(0);
        try {
            List<Products> products = productsdao.findAll();
            for (Products product : products) {
                Object[] row = {product.getId(), product.getproductName(), product.getproductCategory(), product.getproductQuantity(), product.getproductPrice()};
                model.addRow(row);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            showMessageDialog(ex.getMessage());
        }
    }

      private void search() {
        String query = searchTextField.getText();
        if (query.length() == 0) {
            findAll();
        } else {
            model.setRowCount(0);
            try {
                List<Products> products = productsdao.search(searchTextField.getText());
                for (Products product : products) {
                    Object[] row = {product.getId(), product.getproductName(), product.getproductCategory(), product.getproductQuantity(), product.getproductPrice()};
                    model.addRow(row);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                showMessageDialog(ex.getMessage());
            }
        }
    } 
    private void setUpTableModel() {
        productsTable.setModel(model);
        model.setColumnIdentifiers(columns);
    }

    private void setUpPaddingInTextField() {
        setUpBorderFactory(productNameTextField);
        setUpBorderFactory(productCategoryTextField);
        setUpBorderFactory(productQuantityTextField);
        setUpBorderFactory(productPriceTextField);
        setUpBorderFactory(searchTextField);
    }

    private void setUpBorderFactory(JTextField textField) {
        textField.setBorder(BorderFactory.createCompoundBorder(
                textField.getBorder(),
                BorderFactory.createEmptyBorder(5, 10, 5, 5)));
    }

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private Products getValueFromTextField() throws NumberFormatException {
        String nameProduct = productNameTextField.getText();
        String categoryProduct = productCategoryTextField.getText();
        int quantity = Integer.parseInt(productQuantityTextField.getText());
        Double priceProduct = Double.valueOf(productPriceTextField.getText());

        Products product = new Products(nameProduct, categoryProduct, quantity, priceProduct);
        return product;
    }

    private void resetForm() {
        productNameTextField.setText("");
        productCategoryTextField.setText("");
        productQuantityTextField.setText("");
        productPriceTextField.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        productNameLabel = new javax.swing.JLabel();
        productNameTextField = new javax.swing.JTextField();
        productQuantityLabel = new javax.swing.JLabel();
        productQuantityTextField = new javax.swing.JTextField();
        productCategoryLabel = new javax.swing.JLabel();
        productCategoryTextField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        editOrUpdateButton = new javax.swing.JButton();
        productPriceLabel = new javax.swing.JLabel();
        productPriceTextField = new javax.swing.JTextField();
        productTable = new javax.swing.JScrollPane();
        productsTable = new javax.swing.JTable();
        searchTextField = new javax.swing.JTextField();
        searchLabel = new javax.swing.JLabel();
        saleRate = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(153, 0, 153));

        productNameLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        productNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        productNameLabel.setText("Product name");

        productNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productNameTextFieldActionPerformed(evt);
            }
        });

        productQuantityLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        productQuantityLabel.setForeground(new java.awt.Color(255, 255, 255));
        productQuantityLabel.setText("Product quantity");

        productQuantityTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productQuantityTextFieldActionPerformed(evt);
            }
        });

        productCategoryLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        productCategoryLabel.setForeground(new java.awt.Color(255, 255, 255));
        productCategoryLabel.setText("Product category");

        productCategoryTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productCategoryTextFieldActionPerformed(evt);
            }
        });

        saveButton.setText("Add");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        removeButton.setText("Remove");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        editOrUpdateButton.setText("Edit");
        editOrUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editOrUpdateButtonActionPerformed(evt);
            }
        });

        productPriceLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        productPriceLabel.setForeground(new java.awt.Color(255, 255, 255));
        productPriceLabel.setText("Price");

        productPriceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productPriceTextFieldActionPerformed(evt);
            }
        });

        productsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ProductId", "ProductName", "ProductQuantity", "ProductCategory", "ProductPrice"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        productTable.setViewportView(productsTable);

        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });
        searchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTextFieldKeyReleased(evt);
            }
        });

        searchLabel.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        searchLabel.setForeground(new java.awt.Color(255, 255, 255));
        searchLabel.setText("Search");

        saleRate.setText("View sales rate");
        saleRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saleRateActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Manage Items");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(productQuantityLabel))
                            .addComponent(productCategoryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(productPriceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(productNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(productNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                            .addComponent(productCategoryTextField)
                            .addComponent(productQuantityTextField)
                            .addComponent(productPriceTextField)))
                    .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editOrUpdateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saleRate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(productTable, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(searchLabel)
                        .addGap(26, 26, 26)
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                .addGap(343, 343, 343)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchLabel))
                .addGap(31, 31, 31)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(productQuantityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(productQuantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(productCategoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(productCategoryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(productPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(productPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(editOrUpdateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(saleRate))
                    .addComponent(productTable, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        // TODO add your handling code here:
        remove();
    }//GEN-LAST:event_removeButtonActionPerformed

    private void productNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productNameTextFieldActionPerformed

    private void editOrUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editOrUpdateButtonActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_editOrUpdateButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        save();
        findAll();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void productQuantityTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productQuantityTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productQuantityTextFieldActionPerformed

    private void productCategoryTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productCategoryTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productCategoryTextFieldActionPerformed

    private void productPriceTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productPriceTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productPriceTextFieldActionPerformed

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void searchTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyReleased
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_searchTextFieldKeyReleased

    private void saleRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saleRateActionPerformed
        // TODO add your handling code here:
                    SaleRate sr = new SaleRate();
                    sr.setVisible(true);
                    this.setVisible(false);
    }//GEN-LAST:event_saleRateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton editOrUpdateButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel productCategoryLabel;
    private javax.swing.JTextField productCategoryTextField;
    private javax.swing.JLabel productNameLabel;
    private javax.swing.JTextField productNameTextField;
    private javax.swing.JLabel productPriceLabel;
    private javax.swing.JTextField productPriceTextField;
    private javax.swing.JLabel productQuantityLabel;
    private javax.swing.JTextField productQuantityTextField;
    private javax.swing.JScrollPane productTable;
    private javax.swing.JTable productsTable;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton saleRate;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTextField searchTextField;
    // End of variables declaration//GEN-END:variables
}
