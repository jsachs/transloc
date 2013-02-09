#================================================================================
#
# Module to retrieve information from the TransLoc API
#
#================================================================================

from urllib2 import urlopen
from json    import load

url = 'http://api.transloc.com/1.1/'

######## Get the UChicago agency ID ########
def get_agency(name):
    agencies_url = url + 'agencies.json'
    agencies_response = urlopen(agencies_url)
    json_agencies     = load(agencies_response)

    for agency in json_agencies['data']:
        if agency['name'] == name:
            return agency['agency_id']

#<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>

"""
get_routes()

Returns:
    - a list of current route dictionaries
"""
def get_routes():
    agency_id = get_agency('uchicago')
    routes_url = url + 'routes.json?'
    routes_url += 'agencies=' + agency_id
    routes_response = urlopen(routes_url)
    json_routes = load(routes_response)

    if agency_id in json_routes['data']:
        return json_routes['data'][agency_id]
    else:
        print "No active routes at this time."
        return None

"""
get_stops()

Returns:
    - a list of current stop dictionaries
"""
def get_stops():
    agency_id = get_agency('uchicago')
    stops_url = url + 'stops.json?'
    stops_url += 'agencies=' + agency_id
    stops_response = urlopen(stops_url)
    json_stops = load(stops_response)
    return json_stops['data']

"""
get_estimates()

Returns:
    - a list of arrival dictionaries
"""
def get_estimates():
    agency_id = get_agency('uchicago')
    estimates_url = url + 'arrival-estimates.json?'
    estimates_url += 'agencies=' + agency_id
    estimates_response = urlopen(estimates_url)
    json_estimates = load(estimates_response)
    return json_estimates['data']






