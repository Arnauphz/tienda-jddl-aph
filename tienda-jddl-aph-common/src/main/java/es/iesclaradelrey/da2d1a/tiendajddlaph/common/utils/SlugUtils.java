package es.iesclaradelrey.da2d1a.tiendajddlaph.common.utils;

import java.text.Normalizer;

public final class SlugUtils {

    private SlugUtils() {
    }

    public static String toSlug(String texto) {
        if (texto == null || texto.isBlank()) {
            return "";
        }

        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .trim()
                .replaceAll("\\s+", "-")
                .replaceAll("-+", "-");
    }
}