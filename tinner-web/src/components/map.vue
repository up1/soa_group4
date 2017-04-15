<template>
  <div>
    <h5>Select Your Location</h5>
    <div id="map"></div>
  </div>
</template>
<script>
  import GoogleMaps from 'google-maps'
  const GOOGLEMAPCONFIG = {
    api : "AIzaSyDJ4Ues7RjOBgw2-854r4_ux104oYt2T6Y",
    geolocate_url : "https://www.googleapis.com/geolocation/v1/geolocate?key="
  }
  var maps
  var marker
  export default{
    name:'maps',
    data(){
      return {
        lat : 0,
        lng : 0
      }
    },
    mounted(){
      GoogleMaps.KEY = GOOGLEMAPCONFIG.api
      GoogleMaps.load((google) => {
        this.$http.post(GOOGLEMAPCONFIG.geolocate_url+GOOGLEMAPCONFIG.api,{considerIp: "true"}).then(
          data => {
            let LatLng = new google.maps.LatLng(data.body.location.lat,data.body.location.lng)
            this.setLatLng(data.body.location.lat,data.body.location.lng)
            maps = new google.maps.Map(document.getElementById('map'), {
              center: LatLng,
              scrollwheel: true,
              zoom: 12
            })
            marker = new google.maps.Marker({
              position: LatLng,
              map: maps,
              draggable: true,
              animation: google.maps.Animation.DROP,
              title: 'Your Location'
            })
            google.maps.event.addListener(marker, 'dragend', (event) =>{
              this.setLatLng(marker.getPosition().lat(),marker.getPosition().lng())
            })
          }
        )
      })
    },
    methods:{
        setLatLng(latt,lngt){
          this.lat = latt
          this.lng = lngt
          this.$store.commit('setLocationEditInformation',{
            lat:this.lat,
            lng:this.lng
          })
        }
      }
    }
</script>
<style scoped>
  #map{
    width: 100%;
    height: 300px;
  }
  h5{
  	color: #ff1744;
    margin-bottom: 30px
  }
</style>
