#================================================================================
#
# Module for determining the appropriate route and stops given a destination
#
#================================================================================

from api  import get_routes, get_stops
from math import sqrt

"""
lat_lng_dist(a, b)
Calculates the Euclidean distance between two lat/long points.

Parameters:
    - a: a (lat,lng) tuple
    - b: a (lat,lng) tuple

Returns:
    - the Euclidean distance between points
"""
def lat_lng_dist((a1,a2), (b1,b2)):
    dist = sqrt((a1-b1)**2 + (a2-b2)**2)
    return dist

"""
get_nearest_stop(location)
Gets the stop that is nearest to a given location,
constrained by the list of active routes.

Parameters:
    - location: a (lat,lng) tuple

Returns:
    - a stop dictionary
"""
def get_nearest_stop(location):
    routes = get_routes()
    stops = get_stops()
    min_dist = -1
    min_stop = {}

    if not routes:
        return None

    print list(routes[n]['route_id'] for n in range(len(routes)))
    for stop in stops:
        if list(set(stop['routes']) & set(list(routes[n]['route_id'] for n in range(len(routes))))):
            stop_loc = (stop['location']['lat'], stop['location']['lng'])
            dist = lat_lng_dist(location, stop_loc)
            if min_dist < 0 or dist < min_dist:
                min_dist = dist
                min_stop = stop

    return min_stop

"""
nearest_stop_on_route(route, location)
Gets the stop that is nearest to a given location,
constrained by a specific route.

Parameters:
    - route: a route dictionary
    - location: a (lat,lng) tuple

Returns:
    - a stop dictionary
"""
def nearest_stop_on_route(route, location):
    stops = get_stops()
    min_dist = -1
    min_stop = {}

    for stop in stops:
        if route['route_id'] in stop['routes']:
            stop_loc = (stop['location']['lat'], stop['location']['lng'])
            dist = lat_lng_dist(location, stop_loc)
            if min_dist < 0 or dist < min_dist:
                min_dist = dist
                min_stop = stop

    return min_stop

"""
get_directions(start, finish)
Determines the endpoint stops, as well as the route to take,
to get a traveller from start to finish.

Parameters:
    - start: a (lat,lng) tuple
    - finish: a (lat,lng)

Returns:
    - a (stop, route, stop) tuple
"""
def get_directions(start, finish):
    routes = get_routes()
    stops = get_stops()

    if not routes:
        return None

    # First, get the closest destination stop
    f_stop = get_nearest_stop(finish)

    # Get the routes through f_stop
    f_routes = []
    for route in f_stop['routes']:
        for temp_route in routes:
            if temp_route['route_id'] == route:
                f_routes.append(temp_route)

    # Get a list of potential i_stops
    i_stops = []
    for route in f_routes:
        i_stops.append(nearest_stop_on_route(route, start))
    
    # Figure out which potential i_stop is closest to start
    min_dist = -1
    i_stop = []
    for stop in i_stops:
        stop_loc = (stop['location']['lat'], stop['location']['lng'])
        dist = lat_lng_dist(start, stop_loc)
        if min_dist < 0 or dist < min_dist:
            min_dist = dist
            i_stop = stop
    
    # Determine the route that connects these two stops
    route = {}
    for temp_route in routes:
        if temp_route['route_id'] in i_stop['routes'] and temp_route['route_id'] in f_stop['routes']:
            route = temp_route
            break

    return (i_stop, route, f_stop)



