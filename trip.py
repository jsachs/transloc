#==============================================================================#
#
# Module for the Trip class, which stores info about a user's suggested route.
#
#==============================================================================#

class Trip:
    """
    Constructor

    Parameters;
        - trip: a (stop, route, stop) tuple of the suggested trip
    """
    def __init__(self, trip):
        self.i_stop = trip[0]
        self.route  = trip[1]
        self.f_stop = trip[2]

    """
    Determine trip time.
    """

    """
    Set notifications.
    """
