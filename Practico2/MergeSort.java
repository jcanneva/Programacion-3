package Practico2;

public class MergeSort {

	private int[] numbers;
	private int[] helper;
	private int size;

	public void sort(int[] values) {
		this.numbers = values;
		size = values.length;
		this.helper = new int[size];
		mergesort(0, size - 1);
	}

	private void mergesort(int inicio, int fin) {
		// si inicio es menor que fin continua el ordenamiento si inicio no es menor que fin entonces el array está ordenado
		if (inicio < fin) {
			// obtener el indice del elemento que se encuentra en la mitad al ser int redondea el resultado al entero menor
			int medio = (inicio + fin) / 2;
			// ordenar la mitad izquierda del array
			mergesort(inicio, medio);
			// ordenar la mitad derecha del array
			mergesort(medio + 1, fin);
			// combinar ambas mitades ordenadas
			merge(inicio, medio, fin);
		}
	}

	private void merge(int inicio, int medio, int fin) {
		// copiar ambas partes al array helper
		for (int i = inicio; i <= fin; i++)
			helper[i] = numbers[i];
		int i = inicio;
		int j = medio + 1;
		int k = inicio;
		// copiar de manera ordenada al array original los valores de la mitad izquierda o de la derecha
		while (i <= medio && j <= fin) {
			if (helper[i] <= helper[j]) {
				numbers[k] = helper[i];
				i++;
			} else {
				numbers[k] = helper[j];
				j++;
			}
			k++;
		}
		// si quedaron elementos copiarlos al array original
		while (i <= medio) {
			numbers[k] = helper[i];
			k++;
			i++;
		}
		while (j <= fin) {
			numbers[k] = helper[j];
			k++;
			j++;
		}
	}
}
