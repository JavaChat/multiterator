## Readme first

This package is licensed over either the Lesser General Public License, version
3 or later, or the Apache Software License, version 2.0.

The choice of the license is left to the user of this package.

This package requires **Java 8**.

## Versions

None yet!

## What this is

This package implements "multivalued" iterators/streams over `Collection`s and
arrays. There are two iteration modes:

* shift by one,
* shift by the window size.

## Examples

### `Multiterator`, "shift by one" version

Let us have this list as an example:

```java
final List<String> list = Arrays.asList("one", "two", "three", "four");
```

This is how you build a `Multiterator` of size 2 over this list:

```java
// Yes, I did it on purpose to use the vocabulary above
final Multiterator<String> multiterator = Multiterator.ofSize(2).over(list);
```

As the paragraph above mentions, you can also use a `Multiterator` over any
`Collection`, or an array:

```java
Multiterator.ofSize(2).over(someSet);
Multiterator.ofSize(2).over(someArray);
```

A `Multiterator<T>` implements `Iterable<Value<T>>`, so you can do:

```java
// Print all two values
multiterator.forEach(System.out::println);
```

The above will print:

```
<one, two>
<two, three>
<three, four>
```

It also has a `.stream()` method which returns a `Stream<Value<T>>`, so, for
instance, you could use this to print the uppercase values of all "pairs":

```java
multiterator.stream().map(String::toUppercase).forEach(System.out::println)
```

The above will print:

```
<ONE, TWO>
<TWO, THREE>
<THREE, FOUR>
```

### `Multiterator`, windowing mode

In this mode, the window shifts by the window size on each iteration.

Always given the list above, initialize a `Multiterator` like this:

```java
final Multiterator<String> multiterator = Multiterator.ofSize(2)
    .windowed() // Enable window mode
    .over(list);
```

If you then do:

```java
multiterator.forEach(System.out::println);
```

this will now print:

```
<one, two>
<three, four>
```

In this mode, it is of course required that the size of your
collection/list/array be a multiple of the window size!

### `Values`

`Values` are the elements of a `Multiterator`. They themselves implement
`Iterable<T>` and have a `.stream()` method producing a `Stream<T>`.

For instance, always using the same list:

```java
multiterator.ofSize(2).over(list)
    .stream()                       // Stream<Values<String>>
    .flatMap(Values::stream)        // Stream<String>
    .forEach(System.out::println);
```

this will print:

```
one
two
two
three
three
four
```

`Values` implements both `.equals()` and `.hashCode()` as well. This means that:

```java
final List<String> list = Arrays.asList("one", "two", "three", "two", "three");
System.out.println(Multiterator.ofSize(2)
    .over(list)
    .stream()
    .distinct()
    .count()
);
```

will print `3`.

## Future plans

* `{Long,Double,Int}Multiterator` for primitive arrays (wrap `char[]` into `Int*`
  and `float` into `Double*`
* Others! Open to ideas...

