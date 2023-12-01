package com.aplicaion;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @author Hiram Molina
 */
public class Producto extends javax.swing.JFrame {
    private JComboBox<String> cbxCategoria;
    private JTable jtProductos;
    private JTextField txtCodigo;
    private JTextField txtExistencia;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtProveedor;

    public static final String URL = "jdbc:mysql://localhost:3306/puntodeventa";
    public static final String USER = "root";
    public static final String PASS = "admin";

    private static final String INFORMATION_PATH = "informacion_conexion.txt";
    private static final String FILE_ERROR = "Error al escribir en el archivo: ";
    private static final String ERROR = "Error en: ";

    private static final String PRICE = "Precio";
    private static final String NAME = "Nombre";


    private transient PreparedStatement ps;
    private transient ResultSet rs;


    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
            escribirInformacionConexion();
        } catch (Exception e) {
            exceptionManager("Error en la conexión: ", e);
        }
        return con;
    }

    private static void exceptionManager(String text, Exception e) {
        System.out.println(text + e.getMessage());
    }

    public static void escribirInformacionConexion() {
        File archivo = new File(INFORMATION_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));) {
            writer.write(obtenerFecha() + "Conexión exitosa a la base de datos\n");
            writer.write("  URL: " + URL + "\n");
            writer.write("  Usuario: " + USER + "\n");
            writer.write("  Contraseña: " + PASS + "\n");
        } catch (IOException e) {
            exceptionManager(FILE_ERROR, e);
        }
    }

    public static String obtenerFecha() {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return ahora.format(formato);
    }


    private void limpiarCajas() { //LIMPIA TODAS LAS CAJAS

        txtCodigo.setText(null);
        txtNombre.setText(null);
        txtPrecio.setText(null);
        txtProveedor.setText(null);
        txtExistencia.setText(null);
        cbxCategoria.setSelectedIndex(0);
        escribirFechaHoraLimpieza();
    }

    public void escribirFechaHoraLimpieza() {
        File archivo = new File(INFORMATION_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));) {
            String fechaHoraLimpieza = obtenerFecha();
            writer.write(fechaHoraLimpieza + " Boton Cajas limpiadas\n");
        } catch (IOException e) {
            exceptionManager(FILE_ERROR, e);
        }
    }

    public Producto() {
        initComponents();
        txtId.setVisible(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable jTable1 = new javax.swing.JTable();
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        JLabel jLabel2 = new javax.swing.JLabel();
        JLabel jLabel3 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        cbxCategoria = new javax.swing.JComboBox<>();
        JLabel jLabel4 = new javax.swing.JLabel();
        JButton btnGuardar = new javax.swing.JButton();
        JButton btnModificar = new javax.swing.JButton();
        JButton btnEliminar = new javax.swing.JButton();
        JButton btnLimpiar = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        JButton btnBuscar = new javax.swing.JButton();
        JLabel jLabel5 = new javax.swing.JLabel();
        JLabel jLabel6 = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        txtExistencia = new javax.swing.JTextField();
        JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        jtProductos = new javax.swing.JTable();
        JButton btnCargar = new javax.swing.JButton();
        JLabel jLabel7 = new javax.swing.JLabel();
        JMenuBar jMenuBar1 = new javax.swing.JMenuBar();
        JMenu btnAgenda = new javax.swing.JMenu();
        JMenuItem jMenuItem1 = new javax.swing.JMenuItem();
        JMenu jMenu1 = new javax.swing.JMenu();
        JMenuItem jMenuItem2 = new javax.swing.JMenuItem();
        JMenu jMenu2 = new javax.swing.JMenu();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Codigo");

        jLabel2.setText(NAME);

        jLabel3.setText(PRICE);

        cbxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Selecciona", "Comida", "Bebida", "Higiene", "Medicamento"}));

        jLabel4.setText("Categoria:");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(evt -> btnGuardarActionPerformed());

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(evt -> btnModificarActionPerformed());

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(evt -> btnEliminarActionPerformed());

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(evt -> btnLimpiarActionPerformed());

        txtId.setEnabled(false);
        txtId.addActionListener(evt -> txtIdActionPerformed());

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(evt -> btnBuscarActionPerformed());

        jLabel5.setText("ID Proveedor");

        jLabel6.setText("Existencia");

        jtProductos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtProductos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jtProductos.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Codigo", NAME, PRICE, "Cantidad"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jtProductos.setRowHeight(30);
        jScrollPane2.setViewportView(jtProductos);

        btnCargar.setText("Actualizar tabla");
        btnCargar.addActionListener(evt -> btnCargarActionPerformed());

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
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
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
        btnAgenda.addActionListener(evt -> btnAgendaActionPerformed());

        jMenuItem1.setText("Agenda");
        jMenuItem1.addActionListener(evt -> jMenuItem1ActionPerformed());
        btnAgenda.add(jMenuItem1);

        jMenuBar1.add(btnAgenda);

        jMenu1.setText("Pantalla de Venta");

        jMenuItem2.setText("Pantalla Venta");
        jMenuItem2.addActionListener(evt -> jMenuItem2ActionPerformed());
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
    }

    private void btnGuardarActionPerformed() {

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
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar");
                limpiarCajas();
            }
            con.close();
            getConnection();
        } catch (Exception e) {
            exceptionManager(ERROR, e);
        }
    }

    private void escribirGuardarProducto() {
        File archivo = new File(INFORMATION_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));) {
            String fechaHoraGuardarProd = obtenerFecha();
            writer.write(fechaHoraGuardarProd + " Boton Guardar productos\n");
        } catch (IOException e) {
            exceptionManager(FILE_ERROR, e);
        }
    }


    private void txtIdActionPerformed() {
        // ???
    }

    private void btnBuscarActionPerformed() {
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
            } else {
                JOptionPane.showMessageDialog(null, "No existe producto con esa clave");
            }
        } catch (Exception e) {
            exceptionManager(ERROR, e);
        }
    }

    private void escribirBuscar() {
        File archivo = new File(INFORMATION_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));) {
            String fechaHoraBuscarProd = obtenerFecha();

            writer.write(fechaHoraBuscarProd + " Boton Buscar por ID\n");
        } catch (IOException e) {
            exceptionManager(FILE_ERROR, e);
        }
    }

    private void btnModificarActionPerformed() {

        Connection con = getConnection();

        try {
            ps = con.prepareStatement("UPDATE producto SET nombreProducto=?,precioProducto=?,categoriaProducto=?,IdProveedor=?,existencia=? WHERE IdProducto=?");
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
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar");
                limpiarCajas();
            }
            con.close();
            getConnection();
        } catch (Exception e) {
            exceptionManager(ERROR, e);
        }


    }

    private void escribirModificarProd() {
        File archivo = new File(INFORMATION_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));) {
            String fechaHoraModificarProd = obtenerFecha();

            writer.write(fechaHoraModificarProd + " Boton modificar producto\n");
        } catch (IOException e) {
            exceptionManager(FILE_ERROR, e);
        }
    }

    private void btnLimpiarActionPerformed() {
        limpiarCajas();
        escribirBotonLimpiar();
    }

    private void escribirBotonLimpiar() {
        File archivo = new File(INFORMATION_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));) {
            String fechaHoraBotonLimpiar = obtenerFecha();
            writer.write(fechaHoraBotonLimpiar + " Boton limpiar cajas\n");
        } catch (IOException e) {
            exceptionManager(FILE_ERROR, e);
        }
    }

    private void btnEliminarActionPerformed() {

        Connection con = getConnection();

        try {
            ps = con.prepareStatement("DELETE FROM producto WHERE IdProducto=?");
            ps.setString(1, txtId.getText());
            int res = ps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Producto eliminado");
                limpiarCajas();
                escribitbtnEliminar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar");
                limpiarCajas();
            }
            con.close();
            getConnection(); // ???
        } catch (Exception e) {
            exceptionManager(ERROR, e);
        }
    }

    private void escribitbtnEliminar() {
        File archivo = new File(INFORMATION_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));) {
            String fechaHoraBotonEliminar = obtenerFecha();
            writer.write(fechaHoraBotonEliminar + " Boton eliminar producto\n");
        } catch (IOException e) {
            exceptionManager(FILE_ERROR, e);
        }
    }

    private void btnCargarActionPerformed() {

        try {
            DefaultTableModel modelo = new DefaultTableModel();
            jtProductos.setModel(modelo);

            /*PreparedStatement*/
            ps = null;
            rs = null;
            Connection con = getConnection();

            String sql = "SELECT idProducto,nombreProducto,precioProducto,existencia FROM producto";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("Código");
            modelo.addColumn(NAME);
            modelo.addColumn(PRICE);
            modelo.addColumn("Existencia");
            escribirBtnActualizarTabla();

            while (rs.next()) { //PARA PROPORCIONAR LOS DATOS DE CADA FILA CADA CICLO
                Object[] filas = new Object[cantidadColumnas]; //IMPORTANTE DECLARAR EL TAMAÑO DE LOS ARREGLOS 
                // VVVVVVVV PASAR LOS DATOS A TIPO OBJETO
                for (int i = 0; i < cantidadColumnas; i++) {
                    //Igualar los elementos de la variable filas
                    filas[i] = rs.getObject(i + 1);

                }
                modelo.addRow(filas);
            }

        } catch (Exception e) {
            exceptionManager("Error al crear tabla: ", e);
        }
    }

    private void escribirBtnActualizarTabla() {
        File archivo = new File(INFORMATION_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));) {
            String fechaHoraBotonActualizarTabla = obtenerFecha();
            writer.write(fechaHoraBotonActualizarTabla + " Boton actualizar tabla\n");
        } catch (IOException e) {
            exceptionManager(FILE_ERROR, e);
        }
    }

    private void btnAgendaActionPerformed() {
        // ???
    }

    private void jMenuItem1ActionPerformed() {
        AgendaProveedores agendaProveedores = new AgendaProveedores();
        agendaProveedores.setVisible(true);
        escribirbtnAbrirVentanaProv();
    }

    private void escribirbtnAbrirVentanaProv() {
        File archivo = new File(INFORMATION_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));) {
            String fechaHoraBotoAgenda = obtenerFecha();
            writer.write(fechaHoraBotoAgenda + " Boton abrir agenda\n");
        } catch (IOException e) {
            exceptionManager(FILE_ERROR, e);
        }
    }

    private void jMenuItem2ActionPerformed() {
        PantallaVenta pantallaVenta = new PantallaVenta();
        pantallaVenta.setVisible(true);
        escribirbtnAbrirVentanaProv();
    }


    private void btnAbrirVentanaVenta() {// Se usa?
        File archivo = new File(INFORMATION_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));) {
            String fechaHoraBotonVenta = obtenerFecha();
            writer.write(fechaHoraBotonVenta + " Boton abrir pantalla venta\n");
        } catch (IOException e) {
            exceptionManager(FILE_ERROR, e);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        EventQueue.invokeLater(() -> new Producto().setVisible(true));
    }
}
