package sevenWonders.backend;

import java.util.Map;

public interface ResourceCard {
    public Map<Resource, Integer> getResources();
    public boolean isSellable();
}
