/*
 * Copyright 2011 The SCAPE Project Consortium
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package eu.scape_project.xa.tw.gen.types;

/**
 * Message Type
 * @author shsdev https://github.com/shsdev
 * @version 0.3
 */
public enum MsgType {
	/** Request message type */
    REQUEST {

        @Override
        public String toString() {
            return "Request";
        }
    },
    /** Response message type */
    RESPONSE {

        @Override
        public String toString() {
            return "Response";
        }
    }
}
