package appConfigs;

import java.util.HashMap;
import java.util.Map;

public class HeaderConfigs {

	public Map<String, String> defautHeaders() {
		Map<String, String> defaultHeader = new HashMap<String, String>();
		defaultHeader.put("Content-Type", "application/json");

		return defaultHeader;
	}
}
