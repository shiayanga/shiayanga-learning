import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHash {
    public static void main(String[] args) {
        // 定义服务器IP
        String[] servers = {"192.168.31.121", "192.168.3.21", "192.168.1.11", "12.168.31.121", "192.138.3.21", "192.68.1.11"};
        // 定义客户端IP
        String[] clients = {"12.16.31.121", "12.18.3.1", "19.16.1.11"};
        // 计算服务器的Hash，并放到排序的Map中
        SortedMap<Integer,String> hashServerMap = new TreeMap<>();
        for (String server : servers) {
            int hashCode = Math.abs(server.hashCode());
            hashServerMap.put(hashCode,server);
        }
        // 求客户端IP的Hash，取出对应的服务器
        for (String client : clients) {
            int clientHash = Math.abs(client.hashCode());
            SortedMap<Integer, String> tailedMap = hashServerMap.tailMap(clientHash);
            // 取出Hash环上的第一台服务器
            Integer firstKey;
            if (tailedMap.isEmpty()){
                firstKey = hashServerMap.firstKey();
            }else {
                firstKey = tailedMap.firstKey();
            }
            System.out.println("客户端IP\t"+ client +"\t被路由到了服务器\t" + hashServerMap.get(firstKey));
        }
    }
}
