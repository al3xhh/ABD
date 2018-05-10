package abd.p1.view;

import abd.p1.view.listElements.userListCellRender;
import abd.p1.view.observers.questionInvitationObserver;
import abd.p1.controller.questionInvitationController;
import abd.p1.model.Usuario;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 * Clase que muestra una ventana para invitar a un usuario a responder una
 * pregunta en concreto.
 *
 * @author Alejandro Huertas Herrero 3ºB.
 */
@SuppressWarnings("serial")
public class QuestionInvitation extends javax.swing.JDialog implements questionInvitationObserver
{
    private DefaultListModel<Usuario> users;
    private questionInvitationController controller;

    public QuestionInvitation(javax.swing.JDialog parent, boolean modal, questionInvitationController controller)
    {
        super(parent, modal);

        users = new DefaultListModel<>();

        initComponents();

        usersList.setCellRenderer(new userListCellRender());

        this.controller = controller;
        this.controller.setObserver(this);
        this.controller.getAllUsers();

        setLocationRelativeTo(parent);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usersList = new javax.swing.JList<>();
        filterNameButton = new javax.swing.JRadioButton();
        filterJustFriendsButton = new javax.swing.JRadioButton();
        nameTextField = new javax.swing.JTextField();
        acceptButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Invitación a responder una pregunta");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Selecciona al usuario que deseas invitar:");

        usersList.setModel(users
        );
        jScrollPane1.setViewportView(usersList);

        filterNameButton.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        filterNameButton.setForeground(new java.awt.Color(0, 0, 0));
        filterNameButton.setMnemonic('F');
        filterNameButton.setText("Filtrar por nombre:");
        filterNameButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                filterNameButtonActionPerformed(evt);
            }
        });

        filterJustFriendsButton.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        filterJustFriendsButton.setForeground(new java.awt.Color(0, 0, 0));
        filterJustFriendsButton.setMnemonic('o');
        filterJustFriendsButton.setText("Mostrar sólo amigos");
        filterJustFriendsButton.setToolTipText("");
        filterJustFriendsButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                filterJustFriendsButtonActionPerformed(evt);
            }
        });

        nameTextField.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        nameTextField.setForeground(new java.awt.Color(0, 0, 0));
        nameTextField.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                nameTextFieldKeyReleased(evt);
            }
        });

        acceptButton.setBackground(new java.awt.Color(34, 255, 0));
        acceptButton.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        acceptButton.setForeground(new java.awt.Color(0, 0, 0));
        acceptButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AcceptIcon.png"))); // NOI18N
        acceptButton.setMnemonic('A');
        acceptButton.setText("Aceptar");
        acceptButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                acceptButtonActionPerformed(evt);
            }
        });

        cancelButton.setBackground(new java.awt.Color(255, 0, 0));
        cancelButton.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(0, 0, 0));
        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CancelIcon.png"))); // NOI18N
        cancelButton.setMnemonic('C');
        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(filterJustFriendsButton))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(filterNameButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameTextField)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(acceptButton)
                .addGap(18, 18, 18)
                .addComponent(cancelButton)
                .addContainerGap(103, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterNameButton)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterJustFriendsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acceptButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void acceptButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_acceptButtonActionPerformed
    {//GEN-HEADEREND:event_acceptButtonActionPerformed
        if (usersList.getSelectedValue() == null)
            JOptionPane.showMessageDialog(null, "Debes seleccionar un usuario.", "Error", JOptionPane.ERROR_MESSAGE, new javax.swing.ImageIcon(getClass().getResource("/ErrorIcon.png")));
        else
        {
            controller.addQuestionInvitation(usersList.getSelectedValue());
            setVisible(false);
        }
    }//GEN-LAST:event_acceptButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cancelButtonActionPerformed
    {//GEN-HEADEREND:event_cancelButtonActionPerformed
        setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void nameTextFieldKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_nameTextFieldKeyReleased
    {//GEN-HEADEREND:event_nameTextFieldKeyReleased
        if (nameTextField.getText() != null && !nameTextField.getText().trim().isEmpty() && !filterJustFriendsButton.isSelected() && !filterNameButton.isSelected())
            controller.getFiltredUser(nameTextField.getText());
        else if (nameTextField.getText() != null && !nameTextField.getText().trim().isEmpty() && filterJustFriendsButton.isSelected() && filterNameButton.isSelected())
            controller.getFilterFriends(nameTextField.getText());
        else if (nameTextField.getText() != null && !nameTextField.getText().trim().isEmpty() && !filterJustFriendsButton.isSelected() && filterNameButton.isSelected())
            controller.getFiltredUser(nameTextField.getText());
        else if (nameTextField.getText() != null && !nameTextField.getText().trim().isEmpty() && filterJustFriendsButton.isSelected() && !filterNameButton.isSelected())
            controller.getFriends();
        else if (filterJustFriendsButton.isSelected() && filterNameButton.isSelected())
            controller.getFriends();
        else
            controller.getAllUsers();
    }//GEN-LAST:event_nameTextFieldKeyReleased

    private void filterJustFriendsButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_filterJustFriendsButtonActionPerformed
    {//GEN-HEADEREND:event_filterJustFriendsButtonActionPerformed
        if (filterJustFriendsButton.isSelected())
            controller.getFriends();
        else if (nameTextField.getText() != null && !nameTextField.getText().trim().isEmpty() && !filterJustFriendsButton.isSelected())
            controller.getFiltredUser(nameTextField.getText());
        else
            controller.getAllUsers();
    }//GEN-LAST:event_filterJustFriendsButtonActionPerformed

    private void filterNameButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_filterNameButtonActionPerformed
    {//GEN-HEADEREND:event_filterNameButtonActionPerformed
        if (nameTextField.getText() != null && !nameTextField.getText().trim().isEmpty() && filterJustFriendsButton.isSelected() && filterNameButton.isSelected())
            controller.getFilterFriends(nameTextField.getText());
        else if (nameTextField.getText() != null && !nameTextField.getText().trim().isEmpty() && filterJustFriendsButton.isSelected() && !filterNameButton.isSelected())
            controller.getFriends();
    }//GEN-LAST:event_filterNameButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceptButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JRadioButton filterJustFriendsButton;
    private javax.swing.JRadioButton filterNameButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JList<Usuario> usersList;
    // End of variables declaration//GEN-END:variables

    @Override
    public void showUsers(List<Usuario> users)
    {
        this.users.clear();

        users.stream().forEach(this.users::addElement);
    }
}