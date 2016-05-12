#version 330

in  vec2 outTexCoord;
out vec4 fragColor;

uniform sampler2D texture_sampler;

void main()
{
    fragColor = texture(texture_sampler, outTexCoord)+vec4(outTexCoord.x,outTexCoord.y,outTexCoord.y,1.);;
}
