package com.sokratis12gr.modfetcher.util;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.Validate;

import java.util.*;
import java.util.stream.Collectors;

public class NamedRegistry<V> implements Iterable<V> {

    /**
     * A Map which contains all of the names and values that have been registered.
     */
    protected final Map<String, V> registry = Maps.newHashMap();

    /**
     * An array that holds a cache of all registered values. This cache should only be altered
     * internally.
     */
    private V[] valueCache;

    /**
     * Retrieves a value from the registry that is associated with the name passed.
     *
     * @param name The name of the Object you want to retrieve.
     *
     * @return V The value associated with the name. If none is found, expect null.
     */
    public V getValue(String name) {

        return this.registry.get(name);
    }

    /**
     * Registers a new value with the registry with a domain specified. This will also reset
     * the value cache. If the name or value passed are null, the application will crash.
     * Registering a value using a name that already exists is discouraged.
     *
     * @param domain The domain for the entry. This is like a category to register the value
     *               under.
     * @param name   The name to register the value with.
     * @param value  The value to register.
     */
    public V registerValue(String domain, String name, V value) {

        this.registerValue(domain + ":" + name, value);
        return value;
    }

    /**
     * Registers a new value with the registry. This will also reset the value cache. If the
     * name or value passed are null, the application will crash. Registering a value using a
     * name that already exists is discouraged.
     *
     * @param name  The name to register the value with.
     * @param value The value to register.
     */
    public V registerValue(String name, V value) {

        Validate.notNull(name);
        Validate.notNull(value);

        if (this.registry.containsKey(name))
            System.out.println("The name " + name + " is already in use.");

        this.valueCache = null;
        this.registry.put(name, value);
        return value;
    }

    /**
     * Retrieves a List of all registered names that use the passed domain.
     *
     * @param domain The domain to get the names for.
     *
     * @return List<String> A List of all registered names using the passed domain.
     */
    public List<String> getNames(String domain) {

        return this.getNames().stream().filter(name -> name.startsWith(domain + ":")).collect(Collectors.toList());
    }

    /**
     * Retrieves a Set of the names that are currently in use.
     *
     * @return Set<String> The Set of names.
     */
    public Set<String> getNames() {

        return Collections.unmodifiableSet(this.registry.keySet());
    }

    /**
     * Retrieves the values that have been registered under the specified domain.
     *
     * @param domain The domain to get values for.
     *
     * @return List<V> A List of all values registered with the specified domain.
     */
    public List<V> getValues(String domain) {

        final List<V> values = new ArrayList<>();
        this.registry.entrySet().stream().filter(pair -> pair.getKey().startsWith(domain + ":")).forEach(pair -> values.add(pair.getValue()));
        return values;
    }

    /**
     * Retrieves the value cache for the registry. If the value cache is null, then it will be
     * automatically regenerated.
     *
     * @return V[] An array of all the cached values.
     */
    @SuppressWarnings("unchecked")
    public V[] getValues() {

        if (this.valueCache == null)
            this.valueCache = (V[]) this.registry.values().toArray();

        return this.valueCache;
    }

    /**
     * Checks whether or not a domain has been used to register something.
     *
     * @param domain The domain to check for.
     *
     * @return boolean True if the domain has been used.
     */
    public boolean hasDomain(String domain) {

        return this.registry.keySet().stream().filter(name -> name.startsWith(domain + ":")).findFirst() != null;
    }

    /**
     * Checks whether or not a name has already been used in the registry.
     *
     * @param name The name to check for.
     *
     * @return boolean True if the name is in use, false if it is not.
     */
    public boolean hasName(String name) {

        return this.registry.containsKey(name);
    }

    /**
     * Retrieves a value randomly from the registered values that are registered using the
     * provided domain.
     *
     * @param random The Random instance to use for retrieving values.
     * @param domain The domain to limit outcomes to.
     *
     * @return V A random value from the value cache. null if the cache is empty.
     */
    public V getRandomValue(Random random, String domain) {

        final List<V> values = this.getValues(domain);
        return values.isEmpty() ? null : values.get(random.nextInt(values.size()));
    }

    /**
     * Retrieves a value randomly from the cache of values. If the cache of values is empty,
     * then null will be returned.
     *
     * @param random The Random instance to use for retrieving values.
     *
     * @return V A random value from the value cache. null if the cache is empty.
     */
    public V getRandomValue(Random random) {

        final V[] values = this.getValues();
        return values.length == 0 ? null : values[random.nextInt(values.length)];
    }

    /**
     * Clears the entire registry.
     */
    public void clear() {

        this.registry.clear();
    }

    @Override
    public Iterator<V> iterator() {

        return this.registry.values().iterator();
    }
}