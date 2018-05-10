xquery version "3.0";

declare variable $tipo as xs:string external;

for $especie in doc("Abedemon.xml")//especie
let $numAtaques := count($especie/ataques/tiene-ataque)
where $especie/tipo = $tipo
return <resultado id = "{$especie/@id}" nombre = "{$especie/nombre/text()}" num-ataques = "{$numAtaques}"/>