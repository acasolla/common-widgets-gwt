package it.softphone.rd.model.shared;

import java.util.Comparator;
/**
 * Used by the {@link Feed} to ordinate a list
 * @author Alessandro Casolla
 *
 */
public class FeedComparator implements Comparator<Feed>{

	@Override
	public int compare(Feed o1, Feed o2) {
		return -1 * (o1.getDate().compareTo(o2.getDate()));
	}

}
