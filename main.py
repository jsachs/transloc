#================================================================================
#
# Main function for the simplified transloc app.
#
#================================================================================

from urllib2      import urlopen
from json         import load
from route_finder import get_directions


if __name__ == '__main__':
    dest = raw_input("Please enter destination address:")
    dest.replace(' ','+')
    address = 'address=' + dest

    url = 'http://maps.googleapis.com/maps/api/geocode/json?'
    address_url = url + address

    print get_directions((41.792109,-87.600072),(41.796438,-87.605125))
