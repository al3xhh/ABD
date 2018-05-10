xquery version "3.0";

declare variable $yoId as xs:string external;
declare variable $adversarioId as xs:string external;

let $especieAtaca := doc("Abedemon.xml")//especie[@id = $yoId]
let $especieRecibe := doc("Abedemon.xml")//especie[@id = $adversarioId]

for $abedemon in doc("Abedemon.xml")//especie
let $numAtaques := count($abedemon/ataques)
where $abedemon[@id = $yoId]
return if($numAtaques = 0) then 0
       else
            for $ataque in $especieAtaca/ataques
            let $aux := doc("Abedemon.xml")//ataque[@id = $ataque/tiene-ataque/@id]
            return if ($aux/daño/@tipo = $especieRecibe/tipo) then max($aux/daño/@valor)
                   else 0