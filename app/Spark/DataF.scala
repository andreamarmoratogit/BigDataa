package Spark

class DataF(anno:String, mese:String, giorno:String){

  var year:String=anno
  var month:String=mese
  var day:String=giorno

  override def toString: String = year+month+day
}
case class Daily(WBAN:String,Date:String,Tmax:String,TmaxFlag:String,Tmin:String,TminFlag:String,Tavg:String,TavgFlag:String,Depart:String,DepartFlag:String,DewPoint:String,DewPointFlag:String,WetBulb:String,
                 WetBulbFlag:String,Heat:String,HeatFlag:String,Cool:String,CoolFlag:String,Sunrise:String,SunriseFlag:String,Sunset:String,SunsetFlag:String,CodeSum:String,CodeSumFlag:String
                 ,Depth:String,DepthFlag:String,Water1:String,Water1Flag:String,SnowFall:String,SnowFallFlag:String,PrecipTotal:String,PrecipTotalFlag:String,StnPressure:String,StnPressureFlag:String,
                 SeaLevel:String,SeaLevelFlag:String,ResultSpeed:String,ResultSpeedFlag:String,ResultDir:String,ResultDirFlag:String,AvgSpeed:String,AvgSpeedFlag:String,Max5Speed:String,
                 Max5SpeedFlag:String,Max5Dir:String,Max5DirFlag:String,Max2Speed:String,Max2SpeedFlag:String,Max2Dir:String,Max2DirFlag:String) {

}
