/*
 * =============================================================================
 *
 *   Copyright (c) 2011-2012, The THYMELEAF team (http://www.thymeleaf.org)
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
 *
 * =============================================================================
 */
package org.thymeleaf.cache;

import java.util.List;
import java.util.Properties;

import org.thymeleaf.Template;
import org.thymeleaf.dom.Node;







/**
 * 
 *
 * @author Daniel Fern&aacute;ndez
 *
 * @since 2.0.0
 *
 */
public abstract class AbstractCacheManager implements ICacheManager {


    private volatile ICache<String,Template> templateCache;
    private volatile boolean templateCacheInitialized = false;
    
    private volatile ICache<String,List<Node>> fragmentCache;
    private volatile boolean fragmentCacheInitialized = false;
    
    private volatile ICache<String,Object> expressionCache;
    private volatile boolean expressionCacheInitialized = false;
    
    private volatile ICache<String,Properties> messageCache;
    private volatile boolean messageCacheInitialized = false;

    
    public AbstractCacheManager() {
        super();
    }
    
    
    public final ICache<String, Template> getTemplateCache() {
        if (!this.templateCacheInitialized) {
            synchronized(this) {
                if (!this.templateCacheInitialized) {
                    this.templateCache = initializeTemplateCache();
                    this.templateCacheInitialized = true;
                }
            }
        }
        return this.templateCache;
    }

    
    public final ICache<String, List<Node>> getFragmentCache() {
        if (!this.fragmentCacheInitialized) {
            synchronized(this) {
                if (!this.fragmentCacheInitialized) {
                    this.fragmentCache = initializeFragmentCache();
                    this.fragmentCacheInitialized = true;
                }
            }
        }
        return this.fragmentCache;
    }

    
    public final ICache<String, Properties> getMessageCache() {
        if (!this.messageCacheInitialized) {
            synchronized(this) {
                if (!this.messageCacheInitialized) {
                    this.messageCache = initializeMessageCache();
                    this.messageCacheInitialized = true;
                }
            }
        }
        return this.messageCache;
    }

    public final ICache<String, Object> getExpressionCache() {
        if (!this.expressionCacheInitialized) {
            synchronized(this) {
                if (!this.expressionCacheInitialized) {
                    this.expressionCache = initializeExpressionCache();
                    this.expressionCacheInitialized = true;
                }
            }
        }
        return this.expressionCache;
    }

    
    public <K, V> ICache<K, V> getSpecificCache(String name) {
        // No specific caches are used by default
        return null;
    }


    protected abstract ICache<String,Template> initializeTemplateCache();

    protected abstract ICache<String,List<Node>> initializeFragmentCache();
    
    protected abstract ICache<String,Properties> initializeMessageCache();
    
    protected abstract ICache<String,Object> initializeExpressionCache();
    
}