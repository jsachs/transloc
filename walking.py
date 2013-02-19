#==============================================================================#
#
# Module for getting Google Maps walking directions to and from stops.
#
#==============================================================================#

from urllib2      import urlopen
from json         import load

"""
walk_to(start, stop)

Parameters:
    - start: a (lat,lng) tuple
    - stop:  a TransLoc shuttle stop JSON data item

Returns:
    - directions: a list of HTML directions
"""
def walk_to(start, stop):
    url = 'http://maps.googleapis.com/maps/api/directions/json?'
    origin = 'origin=' + str(start[0]) + ',' + str(start[1])
    destination = '&destination=' + \
        str(stop['location']['lat']) + ',' + str(stop['location']['lng'])
    url += origin
    url += destination
    url += '&sensor=true'
    url += '&mode=walking'

    response = urlopen(url)
    json = load(response)

    directions = []
    for step in json['routes'][0]['legs'][0]['steps']:
        directions.append(step['html_instructions'])
    return directions

"""
walk_from(stop, dest):

Parameters:
    - stop: a TransLoc shuttle stop JSON data item
    - dest: a (lat,lng) tuple

Returns:
    - directions: a list of HTML directions
"""
def walk_from(stop, dest):

    url = 'http://maps.googleapis.com/maps/api/directions/json?'
    origin = 'origin=' + \
        str(stop['location']['lat']) + ',' + str(stop['location']['lng'])
    destination = '&destination=' + str(dest[0]) + ',' + str(dest[1])
    url += origin
    url += destination
    url += '&sensor=true'
    url += '&mode=walking'

    response = urlopen(url)
    json = load(response)

    directions = []
    for step in json['routes'][0]['legs'][0]['steps']:
        directions.append(step['html_instructions'])
    return directions
    
