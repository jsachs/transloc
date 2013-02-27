#================================================================================
#
# Main function for the simplified transloc app.
#
#================================================================================

from urllib2      import urlopen
from json         import load
from route_finder import get_directions
from trip         import Trip

if __name__ == '__main__':

    # Begin testing

    d  = raw_input("Please enter destination address:")
    s  = raw_input("Please enter start address:")
    dest  = []
    start = []

    url = 'http://maps.googleapis.com/maps/api/geocode/json?'

    for location, point in zip([s, d],[start, dest]):
        location = location.replace(' ', '+')
        address = 'address=' + location

        address_url = url + address + "&sensor=true"
        address_response = urlopen(address_url)
        json_address = load(address_response)

        lat = json_address['results'][0]['geometry']['location']['lat']
        lng = json_address['results'][0]['geometry']['location']['lng']
        point.append(lat)
        point.append(lng)

    trip = Trip(get_directions(start, dest))
    trip.walking_directions(start, dest)
    print trip
