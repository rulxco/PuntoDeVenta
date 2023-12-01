
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author Hiram Molina
 */
   public class producto extends javax.swing.JFrame {

    public static final String url = "jdbc:mysql://localhost:3306/puntodeventa";
    public static final String user = "root";
    public static final String pass = "admin";

    PreparedStatement ps;
    ResultSet rs;

    public static Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);

            // Lógica para escribir en un archivo
            escribirInformacionConexion();

            // JOptionPane.showMessageDialog(null, "Conexion exitosa");
        } catch (Exception e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
        return con;
    }

                    public static void escribirInformacionConexion() {
                        try {
                            File archivo = new File("informacion_conexion.txt");
                            archivo.createNewFile();

                            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));
//                            writer.write("**********************************************\n");
                            writer.write(ObtenerFecha() + "Conexión exitosa a la base de datos\n");
//                            writer.write("Dia, fecha y hora: "  "\n");
                            writer.write("  URL: " + url + "\n");
                            writer.write("  Usuario: " + user + "\n");
                            writer.write("  Contraseña: " + pass + "\n");
//                            writer.write("**********************************************\n");
                            writer.close();
                        } catch (IOException e) {
                            System.out.println("Error al escribir en el archivo: " + e.getMessage());
                        }
                    }
         
                    public static String ObtenerFecha(){
                        LocalDateTime ahora = LocalDateTime.now();
                        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");
                        return ahora.format(formato);
                    }
   
    
        private void limpiarCajas(){ //LIMPIA TODAS LAS CAJAS
            
            txtCodigo.setText(null);
            txtNombre.setText(null);
            txtPrecio.setText(null);
            txtProveedor.setText(null);
            txtExistencia.setText(null);
            cbxCategoria.setSelectedIndex(0);
            escribirFechaHoraLimpieza();
        }
        
            public void escribirFechaHoraLimpieza() {
            try {
                File archivo = new File("informacion_conexion.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));

                LocalDateTime ahora = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");
                String fechaHoraLimpieza = ahora.format(formatter);

                writer.write(fechaHoraLimpieza + " Boton Cajas limpiadas\n");
                writer.close();
            } catch (IOException e) {
                System.out.println("Error al escribir en el archivo: " + e.getMessage());
            }
        }
            
    public producto() {
        initComponents();
        txtId.setVisible(false);
        setDefaultCloseOperation(producto.DISPOSE_ON_CLOSE);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        cbxCategoria = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        txtExistencia = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtProductos = new javax.swing.JTable();
        btnCargar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        btnAgenda = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Codigo");

        jLabel2.setText("Nombre");

        jLabel3.setText("Precio");

        cbxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona", "Comida", "Bebida", "Higiene", "Medicamento" }));

        jLabel4.setText("Categoria:");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        txtId.setEnabled(false);
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel5.setText("ID Proveedor");

        jLabel6.setText("Existencia");

        jtProductos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtProductos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jtProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Precio", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtProductos.setRowHeight(30);
        jScrollPane2.setViewportView(jtProductos);

        btnCargar.setText("Actualizar tabla");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Inventario");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnModificar)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtExistencia, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(txtProveedor))))
                .addGap(18, 18, 18)
                .addComponent(btnBuscar)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(btnCargar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(jLabel7)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 137, Short.MAX_VALUE)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCargar))
                            .addComponent(btnEliminar))
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar)))
                .addGap(34, 34, 34))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(btnBuscar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAgenda.setText("Agenda Proveedores");
        btnAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendaActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Agenda");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        btnAgenda.add(jMenuItem1);

        jMenuBar1.add(btnAgenda);

        jMenu1.setText("Pantalla de Venta");

        jMenuItem2.setText("Pantalla Venta");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Sobre mí");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//BOTON GUARDAR ------------------------------------------------------------}}}
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        Connection con = getConnection();
        
        try {
            ps = con.prepareStatement("INSERT INTO producto(IdProducto, nombreProducto,precioProducto,categoriaProducto,IdProveedor, existencia) VALUES (?,?,?,?,?,?)");
            ps.setString(1, txtCodigo.getText());
            ps.setString(2, txtNombre.getText());
            ps.setString(3, txtPrecio.getText());
            ps.setString(5, txtProveedor.getText());
            ps.setString(6, txtExistencia.getText());
            ps.setString(4, cbxCategoria.getSelectedItem().toString());
            
            //Ejecutar consulta
            //Igualamos a Int si se ejecutó o no correctamente
            int res = ps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Producto guardado");
                limpiarCajas();
                escribirGuardarProducto();
            }else{
                JOptionPane.showMessageDialog(null, "Error al guardar");
                limpiarCajas();
            }
            con.close();
            con = getConnection();
        } catch (Exception e) {
            System.err.println(e);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void escribirGuardarProducto(){
               try {File archivo = new File("informacion_conexion.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));

                LocalDateTime ahora = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");
                String fechaHoraGuardarProd = ahora.format(formatter);

                writer.write(fechaHoraGuardarProd + " Boton Guardar productos\n");
                writer.close();
            } catch (IOException e) {
                System.out.println("Error al escribir en el archivo: " + e.getMessage());
            }
        }
    
    
    
    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
                        //BOTON BUSCAR _________________________________________________________________________BOTON BUSCAR___________________________________________
    Connection con = getConnection();
     
        try {
            ps = con.prepareStatement("SELECT * FROM producto WHERE IdProducto = ?");
            ps.setString(1, txtCodigo.getText());
            rs = ps.executeQuery();
            
            if (rs.next()) {
                txtId.setText(rs.getString("IdProducto"));
                txtNombre.setText(rs.getString("nombreProducto"));
                txtPrecio.setText(rs.getString("precioProducto"));
                cbxCategoria.setSelectedItem(rs.getString("categoriaProducto"));
                txtProveedor.setText(rs.getString("IdProveedor"));
                txtExistencia.setText(rs.getString("existencia"));
                escribirBuscar();
            }
            else{
                JOptionPane.showMessageDialog(null, "No existe producto con esa clave");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void escribirBuscar(){
               try {File archivo = new File("informacion_conexion.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));

                LocalDateTime ahora = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");
                String fechaHoraBuscarProd = ahora.format(formatter);

                writer.write(fechaHoraBuscarProd + " Boton Buscar por ID\n");
                writer.close();
            } catch (IOException e) {
                System.out.println("Error al escribir en el archivo: " + e.getMessage());
            }
        }
    
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        //Boton Modificar
        
        Connection con = getConnection();
        
        try {
            ps = con.prepareStatement("UPDATE producto SET nombreProducto=?,precioProducto=?,categoriaProducto=?,IdProveedor=?,existencia=? WHERE IdProducto=?");
//            ps.setString(1, txtCodigo.getText());
            ps.setString(1, txtNombre.getText());
            ps.setString(2, txtPrecio.getText());
            ps.setString(3, cbxCategoria.getSelectedItem().toString());
            ps.setString(4, txtProveedor.getText());
            ps.setString(5, txtExistencia.getText());
            ps.setString(6, txtId.getText());

            //Ejecutar consulta
            //Igualamos a Int si se ejecutó o no correctamente
            int res = ps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Producto modificado");
                limpiarCajas();
                escribirModificarProd();
            }else{
                JOptionPane.showMessageDialog(null, "Error al modificar");
                limpiarCajas();
            }
            con.close();
            con = getConnection();
        } catch (Exception e) {
            System.err.println(e);
        }
                                              

    }//GEN-LAST:event_btnModificarActionPerformed
    
    private void escribirModificarProd(){
                   try {File archivo = new File("informacion_conexion.txt");
                    BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));

                    LocalDateTime ahora = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");
                    String fechaHoraModificarProd = ahora.format(formatter);

                    writer.write(fechaHoraModificarProd + " Boton modificar producto\n");
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Error al escribir en el archivo: " + e.getMessage());
                }
            }
    
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCajas();
        escribirBotonLimpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void escribirBotonLimpiar(){
                   try {File archivo = new File("informacion_conexion.txt");
                    BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));

                    LocalDateTime ahora = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");
                    String fechaHoraBotonLimpiar = ahora.format(formatter);

                    writer.write(fechaHoraBotonLimpiar + " Boton limpiar cajas\n");
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Error al escribir en el archivo: " + e.getMessage());
                }
            }
    
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        Connection con = getConnection();
        
        try {
            ps = con.prepareStatement("DELETE FROM producto WHERE IdProducto=?");
////            ps.setString(1, txtCodigo.getText());
//            ps.setString(1, txtNombre.getText());
//            ps.setString(2, txtPrecio.getText());
//            ps.setString(3, cbxCategoria.getSelectedItem().toString());
//            ps.setString(4, txtProveedor.getText());
//            ps.setString(5, txtExistencia.getText());
            ps.setString(1, txtId.getText());

            //Ejecutar consulta
            //Igualamos a Int si se ejecutó o no correctamente
            int res = ps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Producto eliminado");
                limpiarCajas();
                escribitbtnEliminar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al eliminar");
                limpiarCajas();
            }
            con.close();
            con = getConnection();
        } catch (Exception e) {
            System.err.println(e);
        }



        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed
    private void escribitbtnEliminar(){
                       try {File archivo = new File("informacion_conexion.txt");
                        BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));

                        LocalDateTime ahora = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");
                        String fechaHoraBotonEliminar = ahora.format(formatter);

                        writer.write(fechaHoraBotonEliminar + " Boton eliminar producto\n");
                        writer.close();
                    } catch (IOException e) {
                        System.out.println("Error al escribir en el archivo: " + e.getMessage());
                    }
                }
    
    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed

try {
            DefaultTableModel modelo = new DefaultTableModel();
            jtProductos.setModel(modelo);
            
            /*PreparedStatement*/ ps = null;
            rs = null;
            Connection con = getConnection();
            
            String sql = "SELECT idProducto,nombreProducto,precioProducto,existencia FROM producto";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            
            modelo.addColumn("Código");
            modelo.addColumn("Nombre");
            modelo.addColumn("Precio");
            modelo.addColumn("Existencia");
            escribirBtnActualizarTabla();
            
            while (rs.next()) { //PARA PROPORCIONAR LOS DATOS DE CADA FILA CADA CICLO
                Object[] filas = new Object[cantidadColumnas]; //IMPORTANTE DECLARAR EL TAMAÑO DE LOS ARREGLOS 
                // VVVVVVVV PASAR LOS DATOS A TIPO OBJETO
                for(int i = 0; i < cantidadColumnas; i++)
                {
                    //Igualar los elementos de la variable filas
                    filas[i] = rs.getObject(i+1);
                    
                }
                modelo.addRow(filas);
            }
            
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }//GEN-LAST:event_btnCargarActionPerformed

    private void escribirBtnActualizarTabla(){
                       try {File archivo = new File("informacion_conexion.txt");
                        BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));

                        LocalDateTime ahora = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");
                        String fechaHoraBotonActualizarTabla = ahora.format(formatter);

                        writer.write(fechaHoraBotonActualizarTabla + " Boton actualizar tabla\n");
                        writer.close();
                    } catch (IOException e) {
                        System.out.println("Error al escribir en el archivo: " + e.getMessage());
                    }
                }
    
    private void btnAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendaActionPerformed
            
    }//GEN-LAST:event_btnAgendaActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
         AgendaProveedores agendaProveedores = new AgendaProveedores();
                agendaProveedores.setVisible(true);
                escribirbtnAbrirVentanaProv();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void escribirbtnAbrirVentanaProv(){
                       try {File archivo = new File("informacion_conexion.txt");
                        BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));

                        LocalDateTime ahora = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");
                        String fechaHoraBotoAgenda = ahora.format(formatter);

                        writer.write(fechaHoraBotoAgenda + " Boton abrir agenda\n");
                        writer.close();
                    } catch (IOException e) {
                        System.out.println("Error al escribir en el archivo: " + e.getMessage());
                    }
                }
    
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        PantallaVenta pantallaVenta = new PantallaVenta();
                pantallaVenta.setVisible(true);
                escribirbtnAbrirVentanaProv();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    
                    private void btnAbrirVentanaVenta(){
                       try {File archivo = new File("informacion_conexion.txt");
                        BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));

                        LocalDateTime ahora = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");
                        String fechaHoraBotonVenta = ahora.format(formatter);

                        writer.write(fechaHoraBotonVenta + " Boton abrir pantalla venta\n");
                        writer.close();
                    } catch (IOException e) {
                        System.out.println("Error al escribir en el archivo: " + e.getMessage());
                    }
                }
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new producto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu btnAgenda;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cbxCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jtProductos;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtExistencia;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtProveedor;
    // End of variables declaration//GEN-END:variables
}
