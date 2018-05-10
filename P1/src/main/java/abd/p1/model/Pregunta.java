package abd.p1.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.FetchType;

import java.util.ArrayList;

/**
 * Clase que representa una pregunta en la base de datos.
 *
 * @author Alejandro Huertas Herrero 3ºB.
 */
@Entity
@Table(name = "Preguntas")
public class Pregunta
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "texto_pregunta", nullable = false)
    private String enunciado;

    @OneToMany(mappedBy = "preguntaMadre", fetch = FetchType.EAGER)
    private List<Opcion> opciones;

    public Pregunta()
    {
        opciones = new ArrayList<>();
    }

    public Pregunta(String enunciado)
    {
        this.enunciado = enunciado;
        id = null;
        opciones = new ArrayList<>();
    }

    //--------------------------GETTERS--------------------------//
    public Integer getId()
    {
        return id;
    }

    public String getEnunciado()
    {
        return enunciado;
    }

    public List<Opcion> getOpciones()
    {
        return opciones;
    }

    public int getNumOpciones()
    {
        return opciones.size();
    }

    public Opcion getOpcion(int num)
    {
        return opciones.get(num - 1);
    }

    //--------------------------SETTERS--------------------------//
    public void setId(Integer id)
    {
        this.id = id;
    }

    public void setEnunciado(String enunciado)
    {
        this.enunciado = enunciado;
    }

    public void addOpcion(Opcion o)
    {
        o.setPreguntaMadre(this);

        if (o.getNumeroOrden() == 0)
            o.setNumeroOrden(opciones.size() + 1);

        opciones.add(o);
    }

    public void removeOpcion(Opcion o)
    {
        int ordenOpcion = o.getNumeroOrden();

        for (int i = ordenOpcion + 1; i <= opciones.size(); i++)
        {
            opciones.get(i - 1).setNumeroOrden(i - 1);
        }

        opciones.remove(ordenOpcion - 1);
    }

    public void sort(List<Opcion> answers)
    {
        if (answers == null || answers.isEmpty())
            return;

        quickSort(0, answers.size() - 1, answers);
    }

    private void quickSort(int lowerIndex, int higherIndex, List<Opcion> answers)
    {
        int i = lowerIndex;
        int j = higherIndex;

        int pivot = answers.get(lowerIndex + (higherIndex - lowerIndex) / 2).getNumeroOrden();

        while (i <= j)
        {
            while (answers.get(i).getNumeroOrden() < pivot)
                i++;

            while (answers.get(j).getNumeroOrden() > pivot)
                j--;

            if (i <= j)
            {
                exchangeNumbers(i, j, answers);
                i++;
                j--;
            }
        }
        if (lowerIndex < j)
            quickSort(lowerIndex, j, answers);
        if (i < higherIndex)
            quickSort(i, higherIndex, answers);
    }

    private void exchangeNumbers(int i, int j, List<Opcion> answers)
    {
        Opcion temp = answers.get(i);

        answers.set(i, answers.get(j));
        answers.set(j, temp);
    }
}