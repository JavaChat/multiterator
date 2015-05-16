## Readme first

This package is licensed over either the Lesser General Public License, version
3 or later, or the Apache Software License, version 2.0.

The choice of the license is left to the user of this package.

This package requires **Java 8**.

## Versions

None yet!

## What this is

This package implements "multivalued" iterators over `Collection`s. There are
two iteration modes:

* shift by one,
* shift by the window size.

The interface is called `Multiterator`, and for some type `T` it implements
`Iterable<Values<T>>`, from which you can `.get()` values.

Example:

```java
final List<Integer> list = IntStream.rangeClosed(0, 3).boxed()
    .collect(Collectors.toList());

// A simple, shift by 1 multiterator
final Multiterator<Integer> multiterator = Multiterator.ofSize(2)
    .over(list);

/*
 * The following prints:
 * 0, 1
 * 1, 2
 * 2, 3
 */
for (final Values<Integer> values: multiterator)
    System.out.printf("%d, %d\n", values.get(0), values.get(1));

/*
 * Shortcut methods also exist:
 *
 * - values.first() (equivalent to .get(0));
 * - values.second() (equivalent to .get(1)).
 */

// Java 8 style of the above:
multiterator.forEach(
    values -> System.out.printf("%d, %d\n", values.get(0), values.get(1))
);

// A window-shifting multiterator
final Multiterator<Integer> multiterator = Multiterator.ofSize(2)
    .windowed()
    .over(list);

/*
 * The following prints:
 * 0, 1
 * 2, 3
 */
for (final Values<Integer> values: multiterator)
    System.out.printf("%d, %d\n", values.get(0), values.get(1));

// Java 8 style of the above:
multiterator.forEach(
    values -> System.out.printf("%d, %d\n", values.get(0), values.get(1))
);
```

## Future plans

* Make `Multiterator<T>` implement `Stream<Values<T>>`.
* Others! Open to ideas...

