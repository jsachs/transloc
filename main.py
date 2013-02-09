#================================================================================
#
# Main function for the simplified transloc app.
#
#================================================================================

from urllib2      import urlopen
from json         import load
from route_finder import get_directions


if __name__ == '__main__':
    d  = raw_input("Please enter destination address:")
    s = raw_input("Please enter start address:")
    dest = []
    start = []

    url = 'http://maps.googleapis.com/maps/api/geocode/json?'
    
    for loc, point in zip([s, d],[start, dest]):
        loc = loc.replace(" ","+")
        address = 'address=' + loc
    
        address_url = url + address + "&sensor=false"
        print address_url
        address_response = urlopen(address_url)
        json_address = load(address_response)

        lat = json_address['results'][0]['geometry']['location']['lat']
        lng = json_address['results'][0]['geometry']['location']['lng']
        point.append(lat)
        point.append(lng)


    print get_directions((start[0],start[1]), (dest[0],dest[1]))
