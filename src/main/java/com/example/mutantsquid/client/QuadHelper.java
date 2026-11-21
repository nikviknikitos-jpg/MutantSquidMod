package com.example.mutantsquid.client;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import org.joml.Matrix4f;

public final class QuadHelper {
    private QuadHelper() {}

    public static void quad(VertexConsumer vc, Matrix4f m,
                            float x0, float y0, float z0,
                            float x1, float y1, float z1,
                            float x2, float y2, float z2,
                            float x3, float y3, float z3,
                            float nx, float ny, float nz, int light) {
        vc.vertex(m, x0, y0, z0).color(255, 255, 255, 255).texture(0, 1)
                .overlay(OverlayTexture.DEFAULT_UV).light(light).normal(nx, ny, nz).next();
        vc.vertex(m, x1, y1, z1).color(255, 255, 255, 255).texture(1, 1)
                .overlay(OverlayTexture.DEFAULT_UV).light(light).normal(nx, ny, nz).next();
        vc.vertex(m, x2, y2, z2).color(255, 255, 255, 255).texture(1, 0)
                .overlay(OverlayTexture.DEFAULT_UV).light(light).normal(nx, ny, nz).next();
        vc.vertex(m, x3, y3, z3).color(255, 255, 255, 255).texture(0, 0)
                .overlay(OverlayTexture.DEFAULT_UV).light(light).normal(nx, ny, nz).next();
    }

    public static void box(VertexConsumer vc, Matrix4f m,
                           float x0, float y0, float z0,
                           float x1, float y1, float z1, int light) {
        quad(vc, m, x0, y1, z0, x1, y1, z0, x1, y1, z1, x0, y1, z1, 0, 1, 0, light);
        quad(vc, m, x0, y0, z0, x0, y0, z1, x1, y0, z1, x1, y0, z0, 0, -1, 0, light);
        quad(vc, m, x0, y0, z1, x1, y0, z1, x1, y1, z1, x0, y1, z1, 0, 0, 1, light);
        quad(vc, m, x0, y0, z0, x0, y1, z0, x1, y1, z0, x1, y0, z0, 0, 0, -1, light);
        quad(vc, m, x1, y0, z0, x1, y1, z0, x1, y1, z1, x1, y0, z1, 1, 0, 0, light);
        quad(vc, m, x0, y0, z0, x0, y0, z1, x0, y1, z1, x0, y1, z0, -1, 0, 0, light);
    }
}
