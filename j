[10:32 AM] Chintan (Guest)
db.product_catalog.insert (

{​​​​​​

prodid:7000010,

prodname:"nosql distilled",

publisher:"Addison-Wesley",

genre: {​​​​​​academic: "technical"}​​​​​​,

ISBN:1234567,

price:400

}​​​​​​

)

[10:32 AM] Chintan (Guest)
Example:  db.product_catalog.find().pretty()

[10:34 AM] Chintan (Guest)
Syntax:

     db.collection_name.find( {​​​​​query}​​​​​, {​​​​​projection}​​​​​ )

[10:36 AM] Chintan (Guest)
Example:

db.product_catalog.find( {​​​​​ publisher: "Dreamtech" }​​​​​ )

