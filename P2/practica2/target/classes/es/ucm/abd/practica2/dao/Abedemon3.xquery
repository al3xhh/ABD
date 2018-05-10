xquery version "3.0";

declare variable $id as xs:string external;

for $abedemon in doc("Abedemon.xml")//especie
let $tipos := string-join($abedemon/tipo, ", ")
where $abedemon/@id = $id
return <html>
            <head></head>
            <h1>{$abedemon/nombre/text()}</h1>
            <img src = "{$abedemon/url}"/>
            <p>{$abedemon/descripcion/text()}</p>
            <p><b>Tipos: </b>{$tipos}</p>
            <p><b>Ataques: </b>{
                                string-join(for $ataque in $abedemon/ataques/tiene-ataque
                                let $nomAtaque := doc("Abedemon.xml")//ataque[@id = $ataque/@id]
                                order by $nomAtaque/nombre
                                return $nomAtaque/nombre, ", ")
            }</p>
            <p><b>Evoluciones: </b>
                <ul>{
                    for $evolucion in $abedemon/evoluciones/especie
                    return <li><a href = "{$evolucion/@id}">{$evolucion/nombre/text()}</a></li>
                }</ul>
            </p>
        </html>