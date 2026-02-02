package org.serjlemast.dourssproducer.infrastructure.rss;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.serjlemast.dourssproducer.domain.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RssFeedReader {

  @Value("${rss.url}")
  private String url;

  public List<Item> read() {
    try (XmlReader reader = new XmlReader(URI.create(url).toURL())) {
      SyndFeed feed = new SyndFeedInput().build(reader);

      return feed.getEntries().stream()
          .map(e -> mapEntry((SyndEntry) e))
          .collect(Collectors.toList());
    } catch (Exception ex) {
      return Collections.emptyList();
    }
  }

  private Item mapEntry(SyndEntry entry) {
    String guid = entry.getUri();
    String title = entry.getTitle();
    String link = entry.getLink();
    String description = String.valueOf(entry.getDescription().getValue()); // NPE!!!
    Instant publishedAt = entry.getPublishedDate().toInstant(); // dangerous NPE!!!
    return new Item(guid, title, link, description, publishedAt);
  }
}
