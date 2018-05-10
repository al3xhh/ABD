package abd.p1.model;

/**
 * Enumerado que representa los géneros y preferencias de los usuarios que hay
 * en la base de datos.
 *
 * @author Alejandro Huertas Herrero 3ºB
 */
public enum Genero
{
    MUJER("Mujer"), HOMBRE("Hombre"), AMBOS("Ambos");

    private String descripcion;

    private Genero(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public static Genero getGenero(String genero)
    {
        switch (genero)
        {
            case "Mujer":
                return Genero.MUJER;
            case "Hombre":
                return Genero.HOMBRE;
            default:
                return Genero.AMBOS;
        }
    }

    @Override
    public String toString()
    {
        return descripcion;
    }
}