import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 带虚拟节点的一致性Hash算法
 */
public class ConsistentHashWithVirtualNode {
    public static void main(String[] args) {
        // 定义服务器IP
        String[] servers = {"192.168.31.121", "192.168.3.21", "192.168.1.11", "12.168.31.121", "192.138.3.21", "192.68.1.11"};
        // 定义客户端IP
        String[] clients = {"12.16.31.121", "12.18.3.1", "19.16.1.11"};

        SortedMap<Integer, String> serverHash = new TreeMap<>();

        int virtualNodeCount = 3;
        // 开始计算服务器的Hash
        for (String server : servers) {
            int hash = Math.abs(server.hashCode());
            serverHash.put(hash, server);

            // 设置虚拟节点
            for (int i = 0; i < virtualNodeCount; i++) {
                int virtualNodeHash = Math.abs((server + "#" + i).hashCode());
                serverHash.put(virtualNodeHash, "虚拟节点" + i + "映射过来的请求：" + server);
            }
        }

        System.out.println(serverHash.size());
        // 计算客户端请求的服务器Hash
        for (String client : clients) {
            int clientHash = Math.abs(client.hashCode());
            SortedMap<Integer, String> tailedMap = serverHash.tailMap(clientHash);
            if (tailedMap.isEmpty()) {
                Integer firstKey = serverHash.firstKey();
                System.out.println("客户端：" + client + "\t\t路由到了 \t" + serverHash.get(firstKey));
            } else {
                Integer firstKey = tailedMap.firstKey();
                System.out.println("客户端：" + client + "\t\t路由到了 \t" + serverHash.get(firstKey));
            }
        }

    }
}
