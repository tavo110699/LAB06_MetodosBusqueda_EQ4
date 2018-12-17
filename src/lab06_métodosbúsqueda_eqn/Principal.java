package lab06_métodosbúsqueda_eqn;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author gustavo
 */
public class Principal extends Component {

    static FileReader fr;
    static Scanner entrada;
    static String datVector = "";
    static int op = 0;
    static int con = 0;
    static int low = 0;
    static int high;
    static String menu = "";
    static String num = "";
    static int numSearch = 0;

    public static void main(String[] args) {
        entrada = new Scanner(System.in);
        Principal obj = new Principal();
        algoritmoBusqueda alg = new algoritmoBusqueda();
        ArrayList<Integer> array = new ArrayList<Integer>();
        try {
            if (obj.rutaArchivo()) {
                obj.leerArchivo();
                obj.llenarVector(array);
                int[] vector = new int[array.size()];
                for (int i = 0; i < array.size(); i++) {
                    vector[i] = array.get(i);
                }

                high = vector.length;
                do {
                    menu = "";
                    menu += "****** Menú ******\n";
                    menu += "1 busqeda secuencial\n";
                    menu += "2 busqueda binaria \n";
                    menu += "3 busqueda Hash \n";
                    menu += "4 para salir \n";
                    menu += "Elija una opción";
                    op = Integer.parseInt(JOptionPane.showInputDialog(menu));
                    switch (op) {
                        case 1:
                            //Se solicita el dato a buscar.
                            System.out.println("Teclea el número a buscar");
                            numSearch= entrada.nextInt();
                            alg.busquedaSecuencial(vector,numSearch);
                            //código para realizar la búsqueda de los valores, si el dato fue encontrado
//la variable encontrado tomará el valor de true, de lo contrario seguirá siendo
//false
                            for (int elem = 0; elem <= high- 1; elem++) {
                                if (vector[elem] == numSearch) {
                                    alg.encontrado = true;
                                }
                            }
                            if (alg.encontrado) {
                                System.out.println("El número " + num + " fue encontrado");
                            } else {
                                System.out.println("El número " + num + " NO fue encontrado");
                            }
                            break;
                        case 2:
    //Se solicita el dato a buscar.
                            System.out.println("Teclea el número a buscar");
                            numSearch= entrada.nextInt();
                            alg.busquedaBinaria(vector, numSearch);
                            //código para realizar la búsqueda de los valores, si el dato fue encontrado
//la variable encontrado tomará el valor de true, de lo contrario seguirá siendo
//false
                            for (int elem = 0; elem <= high- 1; elem++) {
                                if (vector[elem] == numSearch) {
                                    alg.encontrado = true;
                                }
                            }
                            if (alg.encontrado) {
                                System.out.println("El número " + num + " fue encontrado");
                            } else {
                                System.out.println("El número " + num + " NO fue encontrado");
                            }
                            break;
                        case 3:

    //Se solicita el dato a buscar.
                            System.out.println("Teclea el número a buscar");
                            numSearch= entrada.nextInt();
                            alg.busquedaHash(vector, numSearch);
 
                            break;
                        case 4:

                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "selecciona un numero del 1 al 4");
                            break;
                    }
                } while (op != 4);
            }
        } catch (NumberFormatException e) {
            System.err.println("Alguno de los datos en el archivo no es un número, verifique sus datos");
        } catch (IndexOutOfBoundsException p) {
            System.err.println("La posición especificada no existe");
        }

    }

    public boolean rutaArchivo() {
        boolean encontrado = false;

        try {
            JFileChooser file = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT", "txt");
            file.setFileFilter(filter);
            file.showOpenDialog(this);
            /**
             * abrimos el archivo seleccionado
             */
            File ruta = file.getSelectedFile();
            if (ruta != null) {
                fr = new FileReader(ruta);
                encontrado = true;
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.err.println("Archivo no encontrado");
        }
        return encontrado;
    }

    public void leerArchivo() {
        BufferedReader br = new BufferedReader(fr);
        String datos = null;
        try {
            datos = br.readLine();
            while (datos != null) {
                datVector += datos + "\n";
                datos = br.readLine();
            }
            fr.close();
            br.close();
        } catch (IOException e) {
            //e.printStackTrace();
            System.err.println("Ha ocurrido un error");
        }
    }

    public void llenarVector(ArrayList<Integer> vector) {
        for (int i = 0; i < datVector.length(); i++) {
            int aux = datVector.charAt(i) + "".hashCode();

            if (aux != 32 && aux != 10) {
                num += datVector.charAt(i);
            } else {
                if (num.hashCode() != 0) {

                    vector.add(Integer.parseInt(num));
                }
                num = "";
            }
        }
    }

    public void imprimirVector(int vector[]) {
        for (int i = 0; i < vector.length; i++) {
            System.out.print(vector[i] + "  ");
        }
    }

}

