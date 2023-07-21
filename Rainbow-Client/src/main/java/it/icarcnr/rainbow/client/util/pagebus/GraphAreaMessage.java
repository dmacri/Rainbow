package it.icarcnr.rainbow.client.util.pagebus;

public class GraphAreaMessage {
	
	private Integer networkId;
	
	private String nodeUniqueId;

	public String getNodeUniqueId() {
		return nodeUniqueId;
	}

	public void setNodeUniqueId(String nodeUniqueId) {
		this.nodeUniqueId = nodeUniqueId;
	}

	public Integer getNetworkId() {
		return networkId;
	}

	public void setNetworkId(Integer networkId) {
		this.networkId = networkId;
	}

}
