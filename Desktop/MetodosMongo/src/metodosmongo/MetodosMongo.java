
package metodosmongo;


public class MetodosMongo {

   
    public static void main(String[] args) {
       Metodos metodos = new Metodos();
       
       metodos.conexion();
       metodos.recogerDatos();
       //metodos.insertarDatos();
       metodos.actualizarDatos();
    }
    
}
