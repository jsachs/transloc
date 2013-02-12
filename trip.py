#==============================================================================#
#
# Module for the Trip class, which stores info about a user's suggested route.
#
#==============================================================================#

from api import get_estimate

class Trip:
    """
    Constructor

    Parameters;
        - trip: a (stop, route, stop) tuple of the suggested trip
    """
    def __init__(self, trip):
        # Get the route information
        self.i_stop = trip[0]
        self.route  = trip[1]
        self.f_stop = trip[2]

        # Get time estimates
        

    """
    String

    Shows the start, the route, and the destination as a
    nicely formatted string.
    """
    def __str__(self):
        return "Start: " + self.i_stop['name'] + '\n' + \
        "Route: " + self.route['long_name'] + '\n' + \
        "Destination: " + self.f_stop['name']

    """
    Update trip time.


    """
    def update_estimates(self):
        return

    """
    Set notifications.
    
    This would need to have something to do with getting within a radius of a stop.
    """
