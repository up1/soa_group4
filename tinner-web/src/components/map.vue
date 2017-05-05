<template>
  <div>
    <h5>Select Your Location</h5>
    <div id="map"></div>
  </div>
</template>
<script>
  import GoogleMaps from 'google-maps'
  import { mapGetters } from 'vuex'
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
        lng : 0,
        gmap: undefined
      }
    },computed:mapGetters({
      profile: 'getProfile'
    }),
    mounted(){
      this.setLatLng(this.$store.getters.getProfile.location.lat,this.$store.getters.getProfile.location.lng)
      GoogleMaps.KEY = GOOGLEMAPCONFIG.api
      GoogleMaps.load((google) => {
        if (this.lat === 0 && this.lng === 0) {
          this.$http.post(GOOGLEMAPCONFIG.geolocate_url+GOOGLEMAPCONFIG.api,{considerIp: "true"}).then(
            data => {
              let LatLng = new google.maps.LatLng(data.body.location.lat,data.body.location.lng)
              this.setLatLng(data.body.location.lat,data.body.location.lng)
              this.setGoogleMap(google,LatLng)
            }
          )
        }else{
          let LatLng = new google.maps.LatLng(this.lat,this.lng)
          this.setGoogleMap(google,LatLng)
        }
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
        },
        setGoogleMap(google,LatLng){
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
        },
        google(){
          return maps
        }
      },
      watch:{
        'profile': function() {
          if (maps !== undefined && marker !== undefined) {
            maps.setCenter(new google.maps.LatLng(this.$store.getters.getProfile.location.lat,
                                       this.$store.getters.getProfile.location.lng), 12);
            marker.setPosition(new google.maps.LatLng(this.$store.getters.getProfile.location.lat,
                                       this.$store.getters.getProfile.location.lng))
          }
          this.setLatLng(this.$store.getters.getProfile.location.lat,this.$store.getters.getProfile.location.lng)
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
