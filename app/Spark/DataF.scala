package Spark

class DataF(anno:String, mese:String, giorno:String){

  var year:String=anno
  var month:String=mese
  var day:String=giorno

  override def toString: String = year+month+day
}

