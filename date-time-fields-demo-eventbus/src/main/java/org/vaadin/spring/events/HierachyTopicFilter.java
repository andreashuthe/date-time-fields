/*
 * Copyright 2015 The original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vaadin.spring.events;

/**
 * An implementation of {@link org.vaadin.spring.events.TopicFilter}
 * which validates the topics hierarchical. This means, that the
 * listener filter will be checked as prefixed substring against
 * the event topic.
 * 
 * <ol>
 *   <li>match: <code>eventTopic = "foo.bar"</code> and <code>listenerTopic = "foo"</code></li>
 *   <li>no match: <code>eventTopic = "foo"</code> and <code>listenerTopic = "foo.bar"</code></li>
 *   <li>no match: <code>eventTopic = "foo.bar"</code> and <code>listenerTopic = "foo.not"</code></li>
 * </ol>
 * 
 * @author Marco Luthardt (marco.luthardt@iandme.net)
 */
public class HierachyTopicFilter implements TopicFilter {

    @Override
    public boolean validTopic(String eventTopic, String listenerTopic) {
        return eventTopic.startsWith(listenerTopic);
    }
}
