#version 330

in  vec2 outTexCoord;
out vec4 fragColor;

uniform sampler2D texture_sampler;

vec4 hue_to_rgb(float h)
{
	h = mod((h * 6.), 6.);
	float x = (1. - abs(mod(h, 2.) - 1.));
	vec4 color;

	if (0. <= h && h < 1.) {
		color = vec4(1., x, 0., 1.);
	} else if (1. <= h && h < 2.) {
		color = vec4(x, 1., 0., 1.);
	} else if (2. <= h && h < 3.) {
		color = vec4(0., 1., x, 1.);
	} else if (3. <= h && h < 4.) {
		color = vec4(0., x, 1., 1.);
	} else if (4. <= h && h < 5.) {
		color = vec4(x, 0., 1., 1.);
	} else if (5. <= h && h < 6.) {
		color = vec4(1., 0., x, 1.);
	} else {
		color = vec4(0., 0., 0., 1.);
	}

	return color;
}

void main()
{
    fragColor = texture(texture_sampler, outTexCoord)+hue_to_rgb(outTexCoord.x-outTexCoord.y);
}
