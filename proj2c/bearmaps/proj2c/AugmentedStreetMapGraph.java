package bearmaps.proj2c;

import bearmaps.proj2ab.KDTree;
import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.PointSet;
import bearmaps.proj2ab.WeirdPointSet;
import bearmaps.proj2ab.TrieSet61B;
import bearmaps.proj2ab.MyTrieSet;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {

    List<Node> nodes;
    TrieSet61B Trie;
    Map<String, List<Node>> nameToNode;



    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        // You might find it helpful to uncomment the line below:
        nodes = this.getNodes();
        Trie = new MyTrieSet();
        nameToNode = new HashMap<>();
        for (Node i : nodes) {
            if (name(i.id()) != null) {
                String addStr = cleanString(i.name());
                Trie.add(addStr);
                if (nameToNode.containsKey(addStr)) {
                    nameToNode.get(addStr).add(i);
                } else {
                    List<Node> addOne = new LinkedList<>();
                    addOne.add(i);
                    nameToNode.put(addStr, addOne);
                }
            }
        }
    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        List<Point> points = new LinkedList<>();
        Map<Point, Node> map = new HashMap<>();
        for (Node i : nodes) {
            if (neighbors(i.id()).size() > 0) {
                Point addOne = new Point(i.lon(), i.lat());
                points.add(addOne);
                map.put(addOne, i);
            }
        }
        PointSet solver = new WeirdPointSet(points);
        Point nearestOne = solver.nearest(lon, lat);
        return map.get(nearestOne).id();
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        List<String> result = new LinkedList<>();
        for (String i : Trie.keysWithPrefix(cleanString(prefix))) {
            result.add(nameToNode.get(i).get(0).name());
        }
        return result;
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        LinkedList<Map<String, Object>> result = new LinkedList<>();
        for (Node i : nameToNode.get(cleanString(locationName))) {
            Map<String, Object> addOne = new HashMap();
            addOne.put("lat", i.lat());
            addOne.put("lon", i.lon());
            addOne.put("name", i.name());
            addOne.put("id", i.id());
            result.add(addOne);
        }
        return result;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
