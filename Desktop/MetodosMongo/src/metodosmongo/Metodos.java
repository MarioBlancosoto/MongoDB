
package metodosmongo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class Metodos {
    //Objeto de tipo consulta
    BasicDBObject consulta;
    MongoClient cliente;
    MongoDatabase base;
    MongoCollection<Document>colecion;
     //cursor para poder iterar por la consulta
    MongoCursor<Document> iterar;
    public void conexion(){
        cliente = new MongoClient("localhost",27017);
        //Base a la cual queremos conectarnos.
        base = cliente.getDatabase("test");
        //coleccion a la cual queremos conectarnos.
        colecion = base.getCollection("books");
        //scores
        
        
    }
    
    
    public void recogerDatos(){
        
         consulta = new BasicDBObject("stock",new BasicDBObject("$lt",10));
         FindIterable<Document>cursor1 = colecion.find(consulta);
         
         iterar = cursor1.iterator();
        
         while(iterar.hasNext()){
         Document docu = iterar.next();
         String item = docu.getString("item");
         double stock = docu.getDouble("stock");
         //String tags   = docu.getString("info");
         
             System.out.println("Item :"+item+ " stock:"+stock+" Publisher:");
         
             
         }
         
         
    }
    
    
    public void insertarDatos(){
        
        Document d = new Document("_id",3).append("item", "ABC124").append("stock",36);
        colecion.insertOne(d);
        cliente.close();
        
    }
    
    
    public void actualizarDatos(){
        
         Document condicion = new Document("item","TBD");
        Document operacion = new Document("$inc",new Document().append("stock",5));
        colecion.updateMany(condicion, operacion);
        //Con esto ponemos a 1 el stock a todos
        
        //en la condición ponemos en donde queremos hacer esto
        Document condicion = new Document();
        Document operacion = new Document("$set",new Document().append("stock",1));
        colecion.updateMany(condicion, operacion);
        cliente.close();
        
    }
    
}
